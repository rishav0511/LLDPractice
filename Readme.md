Design a shopping cart, where various types of discount can be applied on Product (productId, quantity). Discount can be fixedAmmount or a percentage based discount.

## 1. Concurrency, Infrastructure & Core Utilities

These problems test multi-threading, thread-safe data structures, and core CS fundamentals.

1. **In-Memory Cache (LRU / LFU / TTL):** Implement O(1) `get`/`put` with custom eviction policies and thread-safe expiration.
2. ~~**Rate Limiter:** Implement Token Bucket, Leaky Bucket, and Sliding Window algorithms in a multi-threaded environment.~~
3. **Task Scheduler / Job Queue:** Design a custom thread pool to handle immediate, delayed, and recurring (CRON) jobs.
4. **Message Queue (Pub/Sub):** Design a Kafka-like system handling topics, partitions, publishers, and consumer offsets.
5. **In-Memory Key-Value Store with Transactions:** Implement `begin()`, `commit()`, and `rollback()` supporting nested transactions and isolation levels.
6. **Database Connection Pool:** Manage a pool of active connections, handling timeouts, idle eviction, and thread-safe leasing.
7. **Software Load Balancer:** Implement request routing using Round Robin, Least Connections, and Consistent Hashing strategies.
8. ~~**In-Memory File System:** Use the **Composite Pattern** to handle directories, files, symbolic links, and permissions.~~
9. **JSON Parser:** Build a lexer and parser to convert stringified JSON into nested object maps and arrays.

## 2. State Machines & Workflow Management

These problems rely heavily on the **State Pattern** and **Command Pattern** to manage complex transitions without nested `if/else` blocks.

10. **Vending Machine:** Manage states (Idle, HasMoney, Dispensing) and calculate minimum change using a greedy approach.
11. **ATM System:** Handle card authentication, cash dispensing via Chain of Responsibility, and transaction rollbacks.
12. **Elevator System:** Optimize wait times using SCAN/Look algorithms and coordinate multiple elevator cars.
13. **Music Player (Spotify):** Manage player states (Playing, Paused, Stopped) and queue logic (Shuffle, Repeat One, Repeat All).
14. **Text Editor (Undo/Redo):** Use the **Command** and **Memento** patterns to apply text changes, revert them, and maintain history.
15. **Order Fulfillment System (Amazon Logistics):** Track a package through strict states (Ordered, Packed, Shipped, Out for Delivery, Delivered) handling exceptions (Lost, Returned).

## 3. Strategy, Pricing & Rule Engines

These problems focus on the **Strategy, Decorator, and Factory Patterns** to build systems that adapt to dynamic rules.

16. **Parking Lot System:** Pluggable pricing strategies (hourly, fixed, weekend surge) and assignment strategies (nearest, random).
17. **Ride-Sharing App (Uber):** Surge pricing strategies and Observer pattern for live location updates.
18. ~~**E-commerce Rule Engine (Cart):** Stacking dynamic discounts, coupons, and tax calculations using the Decorator pattern.~~
19. **Food Delivery App (Swiggy/Zomato):** Restaurant search, dynamic delivery fee calculation, and order tracking.
20. **Notification System:** Routing logic to send alerts via SMS, Email, or Push based on user preferences and rate limits.
21. **Car Rental System (Zoomcar):** Inventory management, booking overlapping dates, and calculating late fees / damage charges.
22. **Hotel Room Booking (MakeMyTrip):** Managing room types, seasonal pricing, and handling concurrent booking attempts for the same room.

## 4. Resource Allocation & Graph/Math Algorithms

These problems blend standard Data Structures and Algorithms with Object-Oriented Design.

23. **Expense Sharing App (Splitwise):** Graph algorithms (min-cash flow) to simplify and reduce the total number of transactions between friends.
24. ~~**Movie Ticket Booking (BookMyShow):** Distributed seat locking (pessimistic locking) and handling concurrent payments.~~
25. **Meeting Scheduler (Google Calendar):** Interval overlap algorithms to find free slots among multiple users and manage RSVPs.
26. **Airline Ticketing System:** Managing flight schedules, seat maps, dynamic pricing based on remaining capacity, and boarding passes.
27. **Library Management System:** Book reservations, handling max checkout limits, calculating late fees, and searching by metadata.
28. **Online Auction System (eBay):** Real-time bidding, maintaining bid history, handling bid rejections, and auto-bidding limits.

## 5. Extensibility, Observers & Game Design

These problems test the Open/Closed Principle—your ability to add new entities or rules without touching core logic.

29. ~~**Logging Framework (Log4j):** Chain of Responsibility for log levels (INFO, DEBUG) and strategies for log destinations (Console, File).~~
30. **Spreadsheet Application (Excel):** Use the **Observer Pattern** to update dependent cells dynamically when a parent cell's formula changes.
31. **Stack Overflow:** Object modeling for Questions, Answers, Comments, Tags, Upvotes, and calculating user Reputation dynamically.
32. **Chess Game:** Complex piece validation, board state management, and move history (for checkmate detection and undo).
33. ~~**Snake and Ladder:** Pluggable board entities (snakes, ladders, portals) that don't break the main game loop.~~
34. **Card Game (Blackjack / Poker):** Managing the deck, shuffling algorithms, player hands, betting rounds, and complex win evaluations.
35. ~**Tic-Tac-Toe (N x N):** Designing a scalable grid and an O(1) or O(N) winner detection algorithm.~
36. **2048 Game:** Grid manipulation, sliding/merging logic, and random tile generation.
