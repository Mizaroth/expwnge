package hax.expwnge.processing.api;

import hax.expwnge.models.ProcessingContext;

public interface CheckRule<T extends ProcessingContext> {
  public boolean check(T processingContext);
}
