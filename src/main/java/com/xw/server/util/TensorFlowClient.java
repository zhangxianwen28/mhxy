package com.xw.server.util;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.coobird.thumbnailator.Thumbnails;
import org.tensorflow.framework.DataType;
import org.tensorflow.framework.TensorProto;
import org.tensorflow.framework.TensorShapeProto;
import tensorflow.serving.Model;
import tensorflow.serving.Predict;
import tensorflow.serving.PredictionServiceGrpc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: xw.z
 * @Date: 2020/3/29 15:36
 * @Description:
 */
public class TensorFlowClient {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        predict(new String[]{"E:\\1.jpg", "E:\\1.jpg"});
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    public static List<Float> predict(String[] imageFilenames) {
        int imageWidth = 100;
        int imageHeight = 100;
        // Generate image file to array
        int[][][][] featuresTensorData = new int[imageFilenames.length][imageWidth][imageHeight][3];

        for (int i = 0; i < imageFilenames.length; i++) {

            // Convert image file to multi-dimension array
            File imageFile = new File(imageFilenames[i]);
            try {

                //BufferedImage image = ImageIO.read(imageFile);
                BufferedImage image = Thumbnails.of(imageFile).forceSize(imageWidth, imageHeight).outputFormat("bmp").asBufferedImage();
                int[][] imageArray = new int[imageHeight][imageWidth];

                for (int row = 0; row < imageHeight; row++) {
                    for (int column = 0; column < imageWidth; column++) {
                        imageArray[row][column] = image.getRGB(column, row);

                        int pixel = image.getRGB(column, row);
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = pixel & 0xff;

                        featuresTensorData[i][row][column][0] = red;
                        featuresTensorData[i][row][column][1] = green;
                        featuresTensorData[i][row][column][2] = blue;
                    }
                }
            } catch (IOException e) {
                System.exit(1);
            }
        }

        // Generate features TensorProto
        TensorProto.Builder featuresTensorBuilder = TensorProto.newBuilder();

        for (int i = 0; i < featuresTensorData.length; ++i) {
            for (int j = 0; j < featuresTensorData[i].length; ++j) {
                for (int k = 0; k < featuresTensorData[i][j].length; ++k) {
                    for (int l = 0; l < featuresTensorData[i][j][k].length; ++l) {
                        featuresTensorBuilder.addFloatVal(featuresTensorData[i][j][k][l] / 255F);
                    }
                }
            }
        }


        TensorShapeProto.Dim featuresDim1 = TensorShapeProto.Dim.newBuilder().setSize(imageFilenames.length).build();
        TensorShapeProto.Dim featuresDim2 = TensorShapeProto.Dim.newBuilder().setSize(imageWidth).build();
        TensorShapeProto.Dim featuresDim3 = TensorShapeProto.Dim.newBuilder().setSize(imageHeight).build();
        TensorShapeProto.Dim featuresDim4 = TensorShapeProto.Dim.newBuilder().setSize(3).build();

        TensorShapeProto featuresShape = TensorShapeProto.newBuilder().addDim(featuresDim1).addDim(featuresDim2).addDim(featuresDim3).addDim(featuresDim4).build();
        featuresTensorBuilder.setDtype(DataType.DT_FLOAT).setTensorShape(featuresShape);
        TensorProto featuresTensorProto = featuresTensorBuilder.build();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("47.93.188.143", 8500).usePlaintext(true).build();
        //这里还是先用block模式
        PredictionServiceGrpc.PredictionServiceBlockingStub stub = PredictionServiceGrpc.newBlockingStub(channel);
        //创建请求
        Predict.PredictRequest.Builder predictRequestBuilder = Predict.PredictRequest.newBuilder();
        //模型名称和模型方法名预设
        Model.ModelSpec.Builder modelSpecBuilder = Model.ModelSpec.newBuilder();
        modelSpecBuilder.setName("half_plus_two");
        modelSpecBuilder.setSignatureName("serving_default");
        predictRequestBuilder.setModelSpec(modelSpecBuilder);

        predictRequestBuilder.putInputs("conv2d_input", featuresTensorProto);
        //访问并获取结果
        Predict.PredictResponse predictResponse = stub.predict(predictRequestBuilder.build());
        TensorProto result = predictResponse.toBuilder().getOutputsOrThrow("dense_1");
        return result.getFloatValList();
    }


}
