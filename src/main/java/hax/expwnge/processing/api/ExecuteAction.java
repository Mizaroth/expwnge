package hax.expwnge.processing.api;

import hax.expwnge.models.ProcessingContext;

public interface ExecuteAction<T extends ProcessingContext> {
  public T execute(T processingContext);
}
