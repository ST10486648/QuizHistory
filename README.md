github repo - https://github.com/ST10486648/QuizHistory.git
Youtube link - https://youtu.be/A_MKg81caGk

# History Quiz App

Welcome to the **History Quiz App**! This Android application is designed to test users' knowledge of historical facts through a fun and interactive True/False quiz.

The app provides instant feedback, tracks scores, and includes a review mode so users can reflect on their answers and improve over time.

---

## Purpose of the App

The primary purpose of this app is to:
- Provide an **educational and engaging quiz experience** on historical topics.
- Allow users to **test and improve their knowledge**.
- Offer **instant feedback** and **final results** to encourage learning.
- Include a **review mode** to help users see which questions they got right or wrong, and learn from their mistakes.

The app is designed for:
- History enthusiasts
- Students preparing for exams
- Anyone looking for a fun way to challenge their historical knowledge

---

## Design Considerations

When designing the History Quiz App, several key considerations were kept in mind:

- **User Experience (UX):**
  - Simple and intuitive interface.
  - Clear prompts to guide the user through the quiz.
  - Highlighting of selected answers for better clarity.
  - Feedback after each question to keep users informed.

- **Accessibility:**
  - Use of color contrast (green/red) to indicate correct/incorrect answers.
  - Clear text labels for buttons and messages.

- **Responsiveness:**
  - Designed to work across a range of Android devices and screen sizes.

- **Quiz Flow:**
  - Prevent users from skipping ahead without answering.
  - Provide a clear start and finish.
  - Include a **review mode** with navigation to go back and forth between questions.

- **Error Handling:**
  - Prompting users if they attempt actions (like answering) before the quiz starts.

- **Code Structure:**
  - Use of clean, modular code with clear naming conventions.
  - Separation of logic for quiz progression, user input, and UI updates.

---

## Use of GitHub

GitHub was used as the **version control platform** for this project to:
- Track code changes over time.
- Collaborate efficiently if working in a team.
- Manage feature development using branches and pull requests.
- Provide a public record of project progress and updates.
- Share the project with others for feedback, collaboration, or portfolio demonstration.

**Git best practices followed:**
- Frequent commits with clear messages.
- Use of `.gitignore` to exclude unnecessary files (e.g., build folders, `.idea`).
- Organized directory structure.
- Descriptive README file for documentation.

---

## 📂 Included Documents

The repository includes the following important files and folders:

| File / Folder                  | Description                                                                                   |
|---------------------------------|---------------------------------------------------------------------------------------------|
| `/app/src/main/java/`          | Contains the Kotlin source code, including `MainActivity`.                                  |
| `/app/src/main/res/`           | Contains the app resources: layouts (`activity_main.xml`), drawables, and values (strings, colors, themes). |
| `AndroidManifest.xml`          | Declares app components and permissions.                                                    |
| `build.gradle`                | Defines project and app-level dependencies and build settings.                              |
| `.gitignore`                  | Specifies files and folders to exclude from Git versioning.                                 |
| `README.md`                   | This documentation file.                                                                   |
