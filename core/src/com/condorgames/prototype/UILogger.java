package com.condorgames.prototype;

import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class UILogger implements ApplicationLogger {

  private TextArea textAreaLogger;

  public UILogger(TextArea textAreaLogger) {
    this.textAreaLogger = textAreaLogger;
  }

  @Override
  public void log(String tag, String message) {
    uiLog(tag, message);
  }

  @Override
  public void log(String tag, String message, Throwable exception) {
    uiLog(tag, message);
  }

  @Override
  public void error(String tag, String message) {
    uiLog(tag, message);

  }

  @Override
  public void error(String tag, String message, Throwable exception) {
    uiLog(tag, message);

  }

  @Override
  public void debug(String tag, String message) {
    uiLog(tag, message);

  }

  @Override
  public void debug(String tag, String message, Throwable exception) {
    uiLog(tag, message);
  }

  private void uiLog(String tag, String message) {
    String newLogMessage = String.format(textAreaLogger.getText() + "\n" + tag + ": " + message);
    textAreaLogger.setText(newLogMessage);
  }
}
