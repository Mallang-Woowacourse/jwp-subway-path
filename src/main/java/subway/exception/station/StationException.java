package subway.exception.station;

import subway.exception.BaseException;
import subway.exception.BaseExceptionType;
import subway.exception.line.LineExceptionType;

public class StationException extends BaseException {

    private final StationExceptionType exceptionType;

    public StationException(final StationExceptionType exceptionType) {
        super(exceptionType.errorMessage());
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
