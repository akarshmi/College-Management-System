# College Management System

A comprehensive desktop application built using Java Swing and AWT for managing college operations efficiently. This system provides an intuitive graphical interface for handling various aspects of college administration.

## Features

### Student Management
- Add, update, and remove student records
- Track student attendance and academic performance
- Manage student admissions and enrollment
- Generate student ID cards and reports
- View and update student personal information

### Faculty Management 
- Maintain faculty profiles and credentials
- Track teaching assignments and schedules
- Manage leave requests and attendance
- Performance evaluation tracking
- Payroll information management

### Course Management
- Create and modify course offerings
- Assign faculty to courses
- Manage course schedules and classroom allocation
- Track course prerequisites and requirements
- Generate course completion certificates

### Administrative Functions
- Fee management and payment tracking
- Exam schedule management
- Library resource management
- Hostel/dormitory management
- Transportation management
- Generate various administrative reports

## Technical Requirements

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL 5.7 or higher
- Minimum 4GB RAM
- Windows/Linux/MacOS operating system

### Dependencies
- MySQL Connector/J
- JCalendar
- Apache POI (for report generation)
- iText (for PDF generation)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/akarshmi/college-management-system.git
```

2. Import the database:
```bash
mysql -u username -p college_db < database/college_db.sql
```

3. Configure database connection:
   - Open `src/config/DBConnection.java`
   - Update database credentials:
```java
private static final String URL = "jdbc:mysql://localhost:3306/college_db";
private static final String USERNAME = "your_username";
private static final String PASSWORD = "your_password";
```

4. Compile and run:
```bash
javac -cp "lib/*" src/*.java
java -cp "lib/*:src" MainApplication
```

## Usage

1. Launch the application
2. Login with your credentials:
   - Admin credentials: admin/admin123
   - Faculty credentials: faculty_id/password
   - Student credentials: student_id/password

3. Navigate through different modules using the main menu
4. Use the toolbar for quick access to common functions
5. Generate reports from the Reports menu

## Project Structure

```
college-management-system/
├── src/
│   ├── controllers/
│   ├── models/
│   ├── views/
│   └── utils/
├── lib/
├── database/
├── resources/
└── docs/
```

## Security Features

- Role-based access control
- Password encryption
- Session management
- Audit logging
- Data backup and recovery options

## Troubleshooting

Common issues and solutions:

1. Database Connection Error
   - Verify MySQL service is running
   - Check database credentials
   - Ensure proper JDBC driver is in classpath

2. UI Rendering Issues
   - Update Java Runtime Environment
   - Check display resolution settings
   - Verify required libraries are present

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.

## Support

For support and queries:
- Email: akarshmi.am@gmail.com
- Documentation: docs/user_manual.pdf
- Issue Tracker: GitHub Issues

## Acknowledgments

- Java Swing/AWT Documentation
- MySQL Community
- Contributing developers and testers
- College administration for requirements and feedback

## Version History

- v1.0.0 (Initial Release)
  - Basic student and faculty management
  - Course management
  - Fee management

- v1.1.0
  - Added library management
  - Enhanced reporting features
  - Bug fixes and performance improvements
