package upp.demo.helper;

import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class MultiSelectHelper {
    public List<String> convert(String fieldValue){
       String[] strArray = fieldValue.split(",");
       List<String> ret = new ArrayList<>();
       for(String str:strArray){
           ret.add(str);
       }
       return ret;
    }
}
