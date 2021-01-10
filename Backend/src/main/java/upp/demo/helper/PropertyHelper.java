package upp.demo.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.dto.PropertyDto;
import upp.demo.globals.Processes;
import upp.demo.globals.PropertyName;
import upp.demo.handler.GenreHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PropertyHelper {

	private final GenreHandler genreHandler;

	public PropertyDto findAvailableProperties(Map<String, String> properties) {
		PropertyDto propertyDto = new PropertyDto();


		if (!properties.isEmpty() && properties != null) {
			for (String key : properties.keySet()) {
				if (key.equals(PropertyName.Keys.INPUT)) {
					propertyDto.setInputs(properties.get(key));
				} else if (key.equals(PropertyName.Keys.VALIDATION)) {
					propertyDto.setConstraints(properties.get(key));
				} else if (key.equals(PropertyName.Keys.CUSTOM)) {
					propertyDto.setCustom(properties.get(key));
					if (properties.get(key).equals(PropertyName.CustomNames.GENRES)) {
						propertyDto.setValues(genreHandler.getGenres());
					}
				}
			}
		}
		return propertyDto;
	}


}
