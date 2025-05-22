package org.example.response;

import org.example.response.components.Resource;
import org.example.response.components.Support;

public record ResourceResponse(
        Resource data,
        Support support
) {
}
