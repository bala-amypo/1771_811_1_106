public interface ComplianceLogRepository
        extends JpaRepository<ComplianceLog, Long> {

    List<ComplianceLog> findBySensorReading_Id(Long readingId);
}
