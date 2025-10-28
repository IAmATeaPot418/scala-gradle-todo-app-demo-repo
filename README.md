# Vulnerable Todo List Application

This is a deliberately vulnerable Todo List application built with Scala (backend) and React (frontend). 
**WARNING: This application contains intentional security vulnerabilities for educational purposes. DO NOT use in production!**

## Known Vulnerabilities

1. SQL Injection vulnerability in todo search
2. Command Injection vulnerability in command execution endpoint
3. Directory Traversal vulnerability in file access
4. Sensitive data exposure (plaintext storage of user tokens)
5. Vulnerable dependencies (old versions with known CVEs)
6. No input validation
7. Unsafe system operations
8. CORS misconfiguration

## Setup Instructions

### Backend (Scala)
1. Navigate to the project root
2. Run: `./gradlew bootRun`

### Frontend (React)
1. Navigate to the frontend directory
2. Run: `npm install`
3. Run: `npm start`

The application will be available at:
- Frontend: http://localhost:3000
- Backend: http://localhost:8080

## Security Notice
This application is intentionally vulnerable and should only be used in isolated development environments for security testing and education purposes.
# scala-gradle-app-repo
