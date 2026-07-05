# Task Management System — Backend API

> Spring Boot REST API with real-time notifications, role-based access control, and automated scheduling.

---

## Overview

A production-ready backend for task and team collaboration. It provides JWT-based authentication, task lifecycle management, real-time WebSocket notifications, daily stand-up tracking, and admin user management.

**Live API Docs:** `https://task-management-xydw.onrender.com/swagger-ui.html`

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Framework | Spring Boot 3.x |
| Security | Spring Security 6.x + JWT (jjwt) |
| Data Access | Spring Data JPA |
| Real-time | WebSocket (STOMP) + SockJS |
| Scheduling | Spring `@Scheduled` (cron) |
| API Docs | OpenAPI 3.0 / SpringDoc |
| Utilities | Lombok |

---

## Features

### Authentication
- JWT login with email + password
- Role-based access (`ADMIN`, `EMPLOYEE`)
- Token validation on every request via `JwtAuthenticationFilter`
- WebSocket CONNECT frame authentication via `WebSocketSecurityInterceptor`

### Task Management
- Admins create and assign tasks to employees
- Task status workflow: `TODO`, `IN_PROGRESS`, `DONE`
- Filter tasks by status, assignee email, or title
- Employees update status of their assigned tasks
- Automatic notification sent to assignee on task creation

### Notifications (Real-time)
- WebSocket push to `/user/queue/notifications`
- Types: `TASK_ASSIGNED`, `TASK_OVERDUE`, `STANDUP_REMAINDER`
- Mark single or all notifications as read
- Unread notification count endpoint
- Read-status updates pushed via WebSocket

### Stand-Up
- Employees submit daily stand-up (once per day)
- Automated reminder at **2:00 PM daily** for users who haven't submitted

### Admin Panel
- Create, list, search (by name/email), and deactivate users
- Audit fields: `createdBy`, `createdAt`, `updatedBy`, `updatedAt`

### Task Comments
- Add comments to tasks
- Permission check: employees can only comment on their assigned tasks; admins can comment on any task
- Retrieve all comments by task ID

### Scheduled Jobs
| Cron | Job | Description |
|------|-----|-------------|
| `0 0 14 * * *` | StandUp Reminder | Notify users who haven't submitted stand-up |
| `0 0 18 * * *` | Overdue Task Alert | Notify assignees of overdue, un-notified tasks |

---

## API Endpoints

### Auth
```
POST /api/auth/login
```

### Tasks
```
POST   /api/task/create
GET    /api/task/all
GET    /api/task/name/{title}
GET    /api/task/status/{status}
GET    /api/task/assignee/{assigneeEmail}
```

### User (Employee)
```
GET    /api/user/profile
GET    /api/user/tasks
PATCH  /api/user/task/{taskId}/status
```

### Admin
```
POST   /api/admin/create
GET    /api/admin/all
GET    /api/admin/name/{fullName}
GET    /api/admin/email/{email}
PATCH  /api/admin/deactivate/{userId}
```

### Notifications
```
GET    /api/notification/my
GET    /api/notification/unread
GET    /api/notification/unread-count
PATCH  /api/notification/read/{notificationId}
PATCH  /api/notification/read-all
```

### Stand-Up
```
POST   /api/standup/submit
```

### Task Comments
```
POST   /api/comment/add
GET    /api/comment/task/{taskId}
```

### WebSocket
```
WS     /ws-notifications
SUB    /user/queue/notifications
SUB    /user/queue/notifications/read-updates
```

---

## Security

- **JWT** tokens carry `email` and `role` claims
- **CORS** configured for:
  - `https://task-management-xydw.onrender.com`
  - `http://localhost:8080`
  - `http://localhost:5173`
- **Endpoint authorization:**
  - `/api/auth/**` → public
  - `/api/admin/**` → `ADMIN` only
  - `/api/task/**`, `/api/user/**`, `/api/notification/**` → `EMPLOYEE` or `ADMIN`
  - `/ws-notifications/**` → public (JWT validated on CONNECT)

---

## Project Structure

```
com.varosha.springboot.taskmanagement/
├── Configuration/
│   ├── ExtractEmail.java
│   ├── JwtAuthenticationFilter.java
│   ├── JwtConfig.java
│   ├── OpenApiConfig.java
│   ├── PasswordConfig.java
│   ├── SecurityConfig.java
│   ├── WebConfig.java
│   ├── WebSocketConfig.java
│   └── WebSocketSecurityInterceptor.java
├── Services/
│   ├── AdminServices.java
│   ├── AuthServices.java
│   ├── NotificationServices.java
│   ├── StandUpServices.java
│   ├── TaskCommentServices.java
│   ├── TaskServices.java
│   └── UserServices.java
├── ServicesImpl/
│   ├── AdminServicesImpl.java
│   ├── AuthServicesImpl.java
│   ├── NotificationServicesImpl.java
│   ├── StandUpReminderService.java
│   ├── StandUpServicesImpl.java
│   ├── TaskCommentServicesImpl.java
│   ├── TaskServicesImpl.java
│   └── UserServicesImpl.java
├── DTO/
│   ├── auth/
│   ├── notification/
│   ├── standUp/
│   ├── task/
│   ├── taskComment/
│   └── user/
├── Models/
├── Repository/
├── Enums/
├── converter/
└── TaskManagementApplication.java
```

---

## Configuration

Requires these properties in `application.yml`:

```yaml
app:
  jwt:
    secret: ${JWT_SECRET}
    expiration: ${JWT_EXPIRATION:86400000}  # 24 hours
```

---

## Author

**Sandeep Mainali** — sawondeep4@gmail.com

---

*Note: This README covers the backend API only. If you'd like frontend details included, share the frontend directory or `pom.xml` / `build.gradle` and I can expand it.*
