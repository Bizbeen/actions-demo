# Class 43 – GitHub Secrets, Build & Test Automation with GitHub Actions

A hands-on beginner repository demonstrating how to securely manage credentials with **GitHub Secrets** and automate Java builds and unit tests using **Maven** and **GitHub Actions**.

---

## 📁 Project Structure

```text
actions-demo/
├── .github/
│   └── workflows/
│       ├── secrets-demo.yml      # Part 1: Demonstrates reading GitHub Secrets
│       └── java-build.yml        # Part 4 & 5: Automates Maven build & JUnit tests
├── src/
│   ├── main/java/com/bank/
│   │   └── BankAccount.java      # Sample banking withdrawal logic
│   └── test/java/com/bank/
│       └── BankAccountTest.java  # Automated JUnit 5 test cases
├── pom.xml                       # Maven dependencies and build configuration
└── README.md
```

---

## 🔐 Part 1: Setting Up GitHub Secrets

Never hardcode passwords, API keys, or tokens inside your source code or workflow YAML files. Store them securely in GitHub Secrets:

1. Open your repository on GitHub and click **Settings**.
2. In the left sidebar, go to **Secrets and variables → Actions**.
3. Click **New repository secret**.
4. Add the following training secret:
   - **Name:** `DEMO_TOKEN`
   - **Secret:** `training-demo-12345`
5. Click **Add secret**.

### Running the Secrets Workflow
1. Go to the **Actions** tab in your repository.
2. Select **GitHub Secrets Demo** from the left sidebar.
3. Click **Run workflow → Run workflow**.
4. Open the workflow logs to verify it outputs `Secret is available`.

---

## ⚙️ Part 2: Build & Test Automation (Maven + GitHub Actions)

### Banking App Logic
The `BankAccount` class simulates a basic account withdrawal:
- Starting Balance: **₹5,000**
- Withdrawal Amount: **₹1,000**
- Expected Remaining Balance: **₹4,000**

### Key Maven Commands

| Command | Description |
| :--- | :--- |
| `mvn clean` | Removes old build output (`target/` folder) |
| `mvn compile` | Compiles Java source code |
| `mvn test` | Executes JUnit unit tests |
| `mvn package` | Packages compiled code into a JAR file |
| `mvn clean verify` | Runs the complete clean, compile, test, and verification lifecycle |

### Running the Build & Test Workflow
1. Go to the **Actions** tab in your repository.
2. Select **Java Build Automation** from the left sidebar.
3. Click **Run workflow → Run workflow**.
4. GitHub Actions will check out the code, set up **JDK 17**, and execute `mvn clean verify`.

---

## 🐛 Part 3: Simulating a Test Failure

To see how CI catches bugs before deployment:

1. Open `src/main/java/com/bank/BankAccount.java` and introduce a bug:
   ```java
   public int withdraw(int balance, int amount) {
       return balance - amount - 1000; // Deliberate bug: returns 3000 instead of 4000
   }
   ```
2. Commit and push the change (or trigger the workflow manually):
   ```bash
   git add .
   git commit -m "Simulate bug in withdrawal logic"
   git push origin main
   ```
3. Check the **Actions** tab—the build will fail with an `AssertionError` (`expected: <4000> but was: <3000>`), preventing faulty code from progressing.
4. Revert the bug, push again, and confirm the build turns green (`✅`).

---

## 🛠️ Quick Troubleshooting

- **Workflow not appearing:** Ensure YAML files are inside `.github/workflows/` and end with `.yml` or `.yaml`.
- **YAML syntax errors:** Always use **2 spaces** for indentation—never use the `Tab` key.
- **`Secret is missing` error:** Double-check that the secret name in **Settings → Secrets and variables → Actions** matches `DEMO_TOKEN` exactly (case-sensitive).
