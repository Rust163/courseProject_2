import java.time.LocalDate;
import java.util.Objects;
public enum FrequencyTask {
        ONETIME(LocalDate.of(2022, 12, 1)),
        DAILY( LocalDate.of(2022, 12, 1)),
        WEEKLY( LocalDate.of(2022, 12, 1)),
        MONTHLY( LocalDate.of(2022, 12, 1)),
        ANNUAL( LocalDate.of(2022, 12, 1)) ;
        private final LocalDate localDate ;
        FrequencyTask(LocalDate ld) {
        Objects.requireNonNull(ld ) ;
        this.localDate = ld ;
        }
        public LocalDate getLocalDate() {
            return this.localDate ;
        }
}
