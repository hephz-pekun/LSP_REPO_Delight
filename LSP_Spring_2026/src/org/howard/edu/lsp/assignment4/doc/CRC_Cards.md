Name: Iyanuoluwa Hephzibah Olanipekun

CRC Cards

CRC Card #1

Class:GroundStationReceiver  
Responsibilities:
- Listens for transponder broadcasts on the airport channel
- Timestamps and buffers incoming raw packets
- Forwards buffered packets to the unpacker for decoding  

Collaborators (if any):
- PacketUnpacker
- TimeService  

Assumptions (if any):
- Radios front-end and frequency configuration are handled; this class receives already‑demodulated byte streams

CRC Card #2

Class:PacketUnpacker  
Responsibilities:
- Unpacks high-density transponder packets into structured message fields
- Validates message integrity and discard malformed packets
- Normalizes units like the altitude and speed to system standards
- Forwards parsed message fields to storage for upsert  

Collaborators (if any):
- GroundStationReceiver
- AircraftRepository  

Assumptions (if any):
- Packet format specification is known and stable

CRC Card #3

Class: Aircraft  
Responsibilities:
- Maintains aircraft identity (squawk/ICAO ID) and current state (type, position, altitude, velocity, heading)
- Updates state from parsed message fields
- Computes derived values (e.g., ground speed/track if needed)  

Collaborators (if any):
- AircraftRepository  

Assumptions (if any):
- One record per unique transponder ID; type information should be present in packets

 CRC Card #4

Class: AircraftRepository  
Responsibilities:
- Stores and upserts Aircraft records by unique ID
- Retrieves aircraft by ID and list all active aircraft
- Expires stale tracks after a timeout  

Collaborators (if any):
- Aircraft
- PacketUnpacker
- QueryHandler
- DisplayModelBuilder
- HazardAnalyzer  

Assumptions (if any):
- In-memory or local persistent store is sufficient for the single‑airport scale

CRC Card #5

Class: DisplayModelBuilder  
Responsibilities:
- Builds a graphics display model from stored aircraft data
- Maps world coordinates to screen coordinates for the airport sector
- Packages a render-ready snapshot for the UI (User Interface)

Collaborators (if any):
- AircraftRepository
- DisplayRefresher
- ControllerConsole  

Assumptions (if any):
- The map/projection for the local airport airspace is available

CRC Card #6

Class: DisplayRefresher  
Responsibilities:
- Triggers a full display model rebuild every 10 seconds
- Notifies the UI(User Interface) to render the latest snapshot
- Throttles or coalesces updates if processing overlaps  

Collaborators (if any):
- TimeService
- DisplayModelBuilder
- ControllerConsole  

Assumptions (if any):
- 10-second cadence is a hard requirement from operations

 CRC Card #7

Class: HazardAnalyzer  
Responsibilities:
- Scans active aircraft and evaluate pairwise separation
- Predicts short-term conflicts using current states (e.g., altitude/heading trends)
- Flags dangerous situations and create alerts  

Collaborators (if any):
- AircraftRepository
- AlertNotifier  
 
Assumptions (if any):
- Single-airport terminal airspace; standard terminal separation minima apply

CRC Card #8

Class: AlertNotifier  
Responsibilities:
- Creates alert objects with severity, rationale, and involved aircraft
- Delivers alerts to the controller UI and maintain alert history
- Clears or downgrades alerts when hazards resolve  

Collaborators (if any):
- HazardAnalyzer
- ControllerConsole
- AircraftRepository  

Assumptions (if any):
- Audible/visual alert modalities are handled by the UI(User Interface) once alerts are pushed

CRC Card #9

Class: ControllerConsole  
Responsibilities:
- Renders the current display snapshot and overlay active alerts
- Accepts controller input for queries (e.g., select aircraft on screen)
- Presents detailed aircraft information on demand  

Collaborators (if any):
- DisplayRefresher
- DisplayModelBuilder
- AlertNotifier
- QueryHandler  

Assumptions (if any):
- UI platform (desktop console) is available and supports event handling

CRC Card #10

Class: QueryHandler  
Responsibilities:
- Parses controller queries (by selection or ID)
- Fetchs aircraft details and recent history from storage
- Formats and returns query results to the console  

Collaborators (if any):
- ControllerConsole
- AircraftRepository  

Assumptions (if any):
- Queries are limited to aircraft shown on the current screen

CRC Card #11

Class: TimeService  
Responsibilities:
- Provides scheduling/timers to trigger periodic tasks
- Generates a 10-second tick for display refresh
- Timestamps received packets for sequencing and expiry logic  

Collaborators (if any):
- DisplayRefresher
- GroundStationReceiver  

Assumptions (if any):
- System clock is synchronized; drift is negligible for 10-second scheduling
