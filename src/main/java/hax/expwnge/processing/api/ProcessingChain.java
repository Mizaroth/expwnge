package hax.expwnge.processing.api;

import hax.expwnge.models.ProcessingContext;

public interface ProcessingChain<T extends ProcessingContext> {
  public abstract T process(T processingContext);
  public abstract ProcessingChain<T> getNextChain();
  public abstract void setNextChain(ProcessingChain<T> nextChain);
  public abstract ProcessingState<T> getState();
  public abstract void setState(ProcessingState<T> state);
}
