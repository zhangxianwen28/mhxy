package com.xw.exception;

/**
 * @Auther: xw.z
 * @Date: 2020/3/7 13:12
 * @Description:
 */
public class RobotCatException extends RuntimeException {

  private Object errorData;

  public RobotCatException() {
    super();
  }

  public RobotCatException(String message) {
    super(message);
  }

  public RobotCatException(String message, Throwable cause) {
    super(message, cause);
  }

  public Object getErrorData() {
    return this.errorData;
  }


  public RobotCatException setErrorData(Object errorData) {
    this.errorData = errorData;
    return this;
  }

}
