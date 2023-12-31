package subway.common.util;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UriUtil {

    public static URI build(final String path, final Object... uriVariableValues) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path(path)
                .buildAndExpand(uriVariableValues)
                .toUri();
    }
}
