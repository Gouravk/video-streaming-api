Video Streaming API

A Springboot application that allows publishing videos, managing metadata, soft deleting, streaming mock video content, listing/searching videos, and tracking engagement (impressions & views).


Decisions & Assumptions:

Domain Design Decisions
1. Engagement metrics stored in separate 'Engagement' entity.
2. Single API for adding or editing the metadata i.e. 'updateVideoMetadata'.
2. Soft delete to preserve engagement history.
3. DTOs to decouple persistence from API contract.
4. Video summary DTO for lightweight list/search responses.
5. Separate increment counters for impressions (load) & views (play).
6. Initial search is simple filter — can write some extensive queries rather than getting all entries.
7. List/search APIs return only required subset of fields.
8. Taking cast as main_cast in Video Entity, since cast is a keyword in SQL

Technical Decisions
- Java 17 + Spring Boot 2.7.x for stability & LTS.
- Maven for build & dependency management.
- H2 for quick testing/demo without extra setup. For production, we can use postgres
- Lombok to reduce boilerplate.
- RESTful structure for clarity.

Assumptions
- Video content is mocked as a string.
- Cast stored as comma-separated string.


How to Run 

Prerequisites
- Java 17+
- Maven
- Git

Steps
```bash
git clone https://github.com/yourusername/video-streaming-api.git
cd video-streaming-api
mvn clean install
mvn spring-boot:run
