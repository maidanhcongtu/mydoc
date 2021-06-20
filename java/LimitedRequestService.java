@RequestScoped
public class LimitedRequestService {
	
	private static Object lock = new Object();
	private static AtomicInteger counter = new AtomicInteger(0);
	private static final maxConcurrent = 5;
	
	public void scanLargeFile(MultipartFormDataInput document) {
		synchronized (ScannerService.lock) {
			ScannerService.counter.incrementAndGet();
			while(ScannerService.counter.get() > maxConcurrent) {}
		}
		try {
			// body of function
			LOGGER.info("Counter: " + ScannerService.counter.get());
		} finally {
			ScannerService.counter.decrementAndGet();
		}
	}
}
