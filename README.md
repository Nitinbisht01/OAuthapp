# OAuthapp
# 🔐 OAuthapp — Full Stack Authentication System

A full-stack authentication microservice built with Spring Boot and React.js,
supporting both traditional email/password login and OAuth2 social login via
Google and GitHub — secured with JWT-based session management.

---

## 🌟 Features

- 📧 **Email/Password Authentication** — Register and login with credentials
- 🔵 **Google OAuth2 Login** — One-click login with Google account
- ⚫ **GitHub OAuth2 Login** — One-click login with GitHub account
- 🔑 **JWT Session Management** — Stateless, token-based authentication
- 🛡️ **Protected Routes** — Frontend routes secured based on auth state
- 👤 **Provider-based Account Creation** — Auto-creates user account on first OAuth login
- 💾 **Token Persistence** — JWT stored and managed on the frontend across sessions

---

## 🛠️ Tech Stack

### Backend
| Technology | Usage |
|---|---|
| Java | Core language |
| Spring Boot | Backend framework |
| Spring Security | Authentication and authorization |
| Spring Data JPA + Hibernate | ORM and database operations |
| JWT (JSON Web Token) | Stateless session management |
| OAuth2 (Google + GitHub) | Social login providers |
| MySQL | User data storage |
| Maven | Build tool |

### Frontend
| Technology | Usage |
|---|---|
| React.js | UI framework |
| JavaScript (ES6+) | Frontend logic |
| Bootstrap / CSS3 | Styling |
| Axios | API communication |
| React Router | Protected route management |


