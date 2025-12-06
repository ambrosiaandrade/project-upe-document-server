<style>
@import url('https://fonts.googleapis.com/css2?family=Anton&family=DM+Mono:ital,wght@0,300;0,400;0,500;1,300;1,400;1,500&family=Roboto:ital,wght@0,100..900;1,100..900&display=swap');

body {
  font-family: "DM Mono";
}
</style>

# Team Contribution Guide

This document outlines the development workflow and standards for the **Project UPE Document Server** team. Following these guidelines is key to maintaining code quality and ensuring smooth collaboration.

---

## 1. Automated Commit Validation (One-Time Setup)

To ensure all commits follow our standards, we use a Git hook that automatically validates every commit message. This check is mandatory.

**You must configure Git to use our shared hooks.** Please run the following commands from the root of the project **once**:

1.  **Configure Git to use the `.githooks` directory:**
    ```bash
    git config core.hooksPath .githooks
    ```

2.  **Make the commit hook executable:**
    (This step is necessary because file permissions are not versioned by Git.)
    ```bash
    chmod +x .githooks/commit-msg
    ```

After this setup, every time you run `git commit`, the hook will automatically check your message format and language. If it fails, the commit will be aborted with an error message explaining what to fix.

---

## 2. Git Workflow: Our Version of GitFlow

We use a simplified GitFlow model. All new work must be done in a dedicated branch created from `develop`.

-   **`main`**: Represents the production-ready code. Only receives merges from `develop` during a release.
-   **`develop`**: The main development branch. All feature and fix branches are merged into `develop`.
-   **`feature/<feature-name>`**: For developing new features (e.g., `feature/add-pdf-signing`).
-   **`fix/<issue-name>`**: For fixing bugs (e.g., `fix/user-login-error`).
-   **`docs/<doc-update>`**: For documentation changes (e.g., `docs/update-readme`).

**Flow:**
1.  Create your `feature/*` or `fix/*` branch from `develop`.
2.  Work on your changes.
3.  Open a Pull Request (PR) back to `develop`.

---

## 3. Commit Message Standard: Conventional Commits

All commit messages **must** follow the [Conventional Commits](https://www.conventionalcommits.org/) specification. This helps us generate automated changelogs and keeps our history clean.

**Format:** `<type>: <description>`

-   **`feat`**: A new feature for the user.
-   **`fix`**: A bug fix for the user.
-   **`refactor`**: Code change that neither fixes a bug nor adds a feature.
-   **`style`**: Changes that do not affect the meaning of the code (white-space, formatting, etc).
-   **`docs`**: Documentation only changes.
-   **`test`**: Adding missing tests or correcting existing tests.
-   **`chore`**: Changes to the build process or auxiliary tools.

**Example:**
```
feat: Add endpoint for document generation
fix: Correct password validation logic
docs: Update team information in README.md
```

---

## 4. Pull Request (PR) Quality Checklist

Before submitting a PR, please ensure you've completed the following:

-   [ ] **Tests Pass:** All existing and new tests are passing (`mvn clean verify`).
-   [ ] **Test Coverage:** New code is covered by tests. Check the Jacoco report.
-   [ ] **SonarQube Clean:** The code has been analyzed by SonarQube and has no new issues.
-   [ ] **Conventional Commits:** All commits in the PR follow our commit standard (enforced by our Git hook).
-   [ ] **Self-Reviewed:** You have reviewed your own code for clarity and potential issues.

---

## 5. Code Review Process

-   **Assign Reviewers:** Assign at least one other team member to review your PR.
-   **Be Clear:** The PR description should clearly explain *what* was changed and *why*.
-   **Be Constructive:** Reviews should be constructive and focused on improving code quality.
-   **Merge After Approval:** A PR can only be merged into `develop` after at least one approval.
