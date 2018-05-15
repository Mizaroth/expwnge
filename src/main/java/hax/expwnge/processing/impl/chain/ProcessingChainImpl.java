package hax.expwnge.processing.impl.chain;

import hax.expwnge.models.ProcessingContext;
import hax.expwnge.processing.api.ProcessingChain;
import hax.expwnge.processing.api.ProcessingState;

public class ProcessingChainImpl implements ProcessingChain<ProcessingContext> {
  private ProcessingState<ProcessingContext> state;
  private ProcessingChain<ProcessingContext> nextChain;
  
  @Override
  public ProcessingContext process(ProcessingContext processingContext) {
    if(getState() != null && getState().checkExecute(processingContext)) {
      processingContext = getState().doExecute(processingContext);
    }
    
    if(getNextChain() != null) {
      processingContext = getNextChain().process(processingContext);
    }
    
    return processingContext;
  }
  
  @Override
  public ProcessingChain<ProcessingContext> getNextChain() {
    return nextChain;
  }
  @Override
  public void setNextChain(ProcessingChain<ProcessingContext> nextChain) {
    this.nextChain = nextChain;
  }
  @Override
  public ProcessingState<ProcessingContext> getState() {
    return state;
  }
  @Override
  public void setState(ProcessingState<ProcessingContext> state) {
    this.state = state;
  }
}
