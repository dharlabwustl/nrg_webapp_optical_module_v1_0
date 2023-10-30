package org.nrg.xnat.workflow.listeners;

import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.nrg.xdat.om.WrkWorkflowdata;
import org.nrg.xft.event.Event;
import org.nrg.xnat.workflow.PipelineEmailHandlerAbst;

import java.util.Map;

public class OISProcEmailHandler extends PipelineEmailHandlerAbst {
    final static Logger logger = Logger.getLogger(OISProcEmailHandler.class);

    private final String PIPELINE_NAME = "ois/ois.xml";
    private final String PIPELINE_NAME_PRETTY = "OIS Processing Pipeline";


    public void handleEvent(Event e, WrkWorkflowdata wrk) {
        Map<String,Object> params = Maps.newHashMap();
        params.put("pipelineName",PIPELINE_NAME_PRETTY);
        if (completed(e)) {
            standardPipelineEmailImpl(e, wrk, PIPELINE_NAME, DEFAULT_TEMPLATE_SUCCESS, "processed with "+PIPELINE_NAME_PRETTY, "processed.lst", params);
        } else if (failed(e)) {
            standardPipelineEmailImpl(e, wrk, PIPELINE_NAME, DEFAULT_TEMPLATE_FAILURE, DEFAULT_SUBJECT_FAILURE, "processed.lst", params);
        }
    }
}
