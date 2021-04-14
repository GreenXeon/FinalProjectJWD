package by.epam.jwd.finalproj.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomTag extends TagSupport {

    private static final String COPYRIGHT_TAG = "Made by Zakhar Shishkin, 2021. All rights reserved.";

    private final Logger logger = LogManager.getLogger(CustomTag.class);

    @Override
    public int doStartTag() {
        try {
            pageContext.getOut().write(COPYRIGHT_TAG);
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        return SKIP_BODY;
    }
}
