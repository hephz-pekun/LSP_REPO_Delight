Part 1:
Shared Resource #1: nextId (the shared counter used to generate unique request IDs)
Shared Resource #2: requests (the shared `ArrayList` storing all requests)
Concurrency Problem:
Race conditions, which can result in duplicate or missing request IDs and possible corruption of the `requests` list.
Why addRequest() is unsafe:addRequest() is not synchronized, so multiple threads can call getNextId() at the same time and receive the same ID.It also modifies a non–thread-safe ArrayList concurrently, which can lead to inconsistent or corrupted data.

Part 2:
Fix A: This fix incorrect because 
- Synchronizing `getNextId()` ensures that ID generation is atomic, so duplicate IDs are avoided.
- However, `addRequest()` still performs multiple operations (ID generation + adding to `ArrayList`) without synchronization.
-  The `requests` list is still accessed concurrently and remains unsafe.

Fix B: This is correct because
-  Synchronizing `addRequest()` ensures that the entire sequence (getting the ID, creating the request, and adding it to the list) executes atomically.
- This prevents race conditions on both `nextId` and the `requests` list.

Fix C: This is incorrect because
- Synchronizing `getRequests()` only protects read access to the list.
- It does not prevent concurrent modifications to `nextId` or `requests` during `addRequest()`, so race conditions still exist.

Part 3:
No, getNextId() should not be public. According to Arthur Riel’s heuristics, internal data and behavior should be hidden to preserve encapsulation. Exposing `getNextId()` allows external classes to interfere with ID generation, potentially breaking class invariants and correctness.

Part 4:

Description:
An alternative to `synchronized` is to use atomic variables and thread-safe collections from `java.util.concurrent`. AtomicInteger provides lock-free, thread-safe ID generation, and a concurrent collection ensures safe modification of the request list. This approach improves scalability and avoids explicit locking.

Code Snippet:
```
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

private AtomicInteger nextId = new AtomicInteger(1);
private List<String> requests = new CopyOnWriteArrayList<>();

public void addRequest(String studentName) {
    int id = nextId.getAndIncrement();
    requests.add("Request-" + id + " from " + studentName);
}
```
