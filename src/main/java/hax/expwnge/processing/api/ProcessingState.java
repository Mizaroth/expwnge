package hax.expwnge.processing.api;

import java.util.Map;

import hax.expwnge.models.ProcessingContext;

public interface ProcessingState<T extends ProcessingContext> {
  public abstract Boolean checkExecute(T processingContext);
  public abstract T doExecute(T processingContext);
  public abstract Map<String, Object> getActionMap();
  public abstract void setActionMap(Map<String, Object> actionMap);
  public abstract Map<String, Object> getGuardMap();
  public abstract void setGuardMap(Map<String, Object> guardMap);
}
