package subway.path.application;

import static subway.line.exception.station.StationExceptionType.NOT_FOUND_STATION;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subway.line.domain.LineRepository;
import subway.line.domain.Station;
import subway.line.domain.StationRepository;
import subway.line.exception.station.StationException;
import subway.path.application.dto.ShortestRouteResponse;
import subway.path.domain.DiscountPolicy;
import subway.path.domain.Path;
import subway.path.domain.PaymentLines;
import subway.path.domain.PaymentPolicy;
import subway.path.domain.ShortestRouteService;

@Transactional(readOnly = true)
@Service
public class PathService {

    private final StationRepository stationRepository;
    private final LineRepository lineRepository;
    private final ShortestRouteService shortestRouteService;
    private final PaymentPolicy paymentPolicy;
    private final DiscountPolicy discountPolicy;

    public PathService(final StationRepository stationRepository,
                       final LineRepository lineRepository,
                       final ShortestRouteService shortestRouteService,
                       final PaymentPolicy paymentPolicy,
                       final DiscountPolicy discountPolicy) {
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
        this.shortestRouteService = shortestRouteService;
        this.paymentPolicy = paymentPolicy;
        this.discountPolicy = discountPolicy;
    }

    public ShortestRouteResponse findShortestRoute(final String startStationName, final String endStationName) {
        final Station start = findStationByName(startStationName);
        final Station end = findStationByName(endStationName);
        final Path path = shortestRouteService.shortestRoute(
                new Path(lineRepository.findAll()), start, end
        );
        final Path continousPath = path.continuousPathWithStartStation(start);
        final PaymentLines paymentLines = new PaymentLines(continousPath, paymentPolicy, discountPolicy);
        return ShortestRouteResponse.from(paymentLines.lines(), paymentLines.discountFee());
    }

    private Station findStationByName(final String stationName) {
        return stationRepository.findByName(stationName)
                .orElseThrow(() -> new StationException(NOT_FOUND_STATION));
    }
}
