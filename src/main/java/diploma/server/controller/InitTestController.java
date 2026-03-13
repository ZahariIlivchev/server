package diploma.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.async.DeferredResult;

import diploma.server.model.type.ApiResponse;
import diploma.server.thread.ThreadExecutorService;


@Controller
public class InitTestController {

    @Autowired
    ThreadExecutorService thread;

    @GetMapping("/")
    public DeferredResult<ResponseEntity<ApiResponse<?>>> initTest() {
        DeferredResult<ResponseEntity<ApiResponse<?>>> res = new DeferredResult<>();
 
        Runnable process = () -> {
            res.setResult(ResponseEntity.ok()
                .header("Test_Header", "Test_Header_Value")
                .body(new ApiResponse<Integer>("Server online.", 200))
            );
        };
        
        thread.execute(process);
        return res;
    }
    
    
}
