package hax.expwnge.processing.impl.state;

import java.util.Map;

import hax.expwnge.models.ProcessingContext;
import hax.expwnge.processing.api.CheckRule;
import hax.expwnge.processing.api.ExecuteAction;
import hax.expwnge.processing.api.ProcessingState;

@SuppressWarnings("unchecked")
public class ProcessingStateImpl implements ProcessingState<ProcessingContext> {
  private Map<String, Object> guardMap;
  private Map<String, Object> actionMap;
  
  @Override
  public Boolean checkExecute(ProcessingContext processingContext) {
    return ((CheckRule<ProcessingContext>)getGuardMap().get(null)).check(processingContext);
  }
  
  @Override
  public ProcessingContext doExecute(ProcessingContext processingContext) {
    ExecuteAction<ProcessingContext> executeAction = (ExecuteAction<ProcessingContext>)getActionMap().get(null);
    return executeAction.execute(processingContext);
  }
  
  public Map<String, Object> getActionMap() {
    return actionMap;
  }
  public void setActionMap(Map<String, Object> actionMap) {
    this.actionMap = actionMap;
  }
  public Map<String, Object> getGuardMap() {
    return guardMap;
  }
  public void setGuardMap(Map<String, Object> guardMap) {
    this.guardMap = guardMap;
  }
}
