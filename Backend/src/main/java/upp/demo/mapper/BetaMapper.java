package upp.demo.mapper;

import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.elastic.model.BetaIndex;
@Component
public class BetaMapper {
    public EnumDto convert(BetaIndex betaIndex) {
        EnumDto beta = new EnumDto();
        beta.setId(betaIndex.getId());
        beta.setValue(betaIndex.getEmail());
        return beta;
    }
}
