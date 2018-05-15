package hax.expwnge.processing.impl.rules;

import org.springframework.beans.factory.annotation.Value;

import hax.expwnge.models.ProcessingContext;
import hax.expwnge.processing.api.CheckRule;

public class CheckExecuteChrome implements CheckRule<ProcessingContext> {
  @Value("#{app.steps_chrome}")
  private boolean chromeStep;

  @Override
  public boolean check(ProcessingContext processingContext) {
    return chromeStep;
  }
  
  public boolean isChromeStep() {
    return chromeStep;
  }

  public void setChromeStep(boolean chromeStep) {
    this.chromeStep = chromeStep;
  }
}
