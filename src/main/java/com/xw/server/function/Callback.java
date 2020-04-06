package com.xw.server.function;

/**
 * @Auther: xw.z
 * @Date: 2020/3/9 08:57
 * @Description:
 */
public interface Callback<T> {
  void onFailure(Throwable caught);

  void onSuccess(T result);
}
