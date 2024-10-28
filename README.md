# HobbyList

A desktop application for managing hobby activities, featuring a Command Line Interface (CLI) with GUI support. Built with Java and JavaFX, HobbyList helps users efficiently track and organize their activities.

![Java](https://img.shields.io/badge/Java-11-orange.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-latest-blue.svg)
[![codecov](https://codecov.io/gh/AY2223S1-CS2103T-T12-3/tp/branch/master/graph/badge.svg?token=N1GXOPW52H)](https://codecov.io/gh/AY2223S1-CS2103T-T12-3/tp)

![Ui](docs/images/default_gui.png)

## 🎯 Features

### Core Functionality

- **Activity Management**
  - Add/Edit/Delete activities
  - Track activity status (Upcoming/Ongoing/Completed)
  - Add descriptions and tags
  - Rate and review activities

### Advanced Features

- **Custom Command Aliases**

  - Rename commands for personalization
  - Save custom command configurations

- **Theme Customization**

  - Multiple theme options
  - Persistent theme settings

- **Smart Search**
  - Find activities by keywords
  - Filter by date ranges
  - Search by ratings

## 🏗️ Architecture

```
┌─────────────┐
│     UI      │
└─────┬───────┘
      │
┌─────┴───────┐     ┌─────────────┐
│    Logic    │────►│   Model     │
└─────┬───────┘     └──────┬──────┘
      │                    │
┌─────┴────────────┐      │
│    Storage       │◄─────┘
└──────────────────┘
```

### Components

1. **UI Component**

![Structure of the UI Component](docs/images/UiClassDiagram.png)

```java
public interface Ui {
    void start(Stage primaryStage);
    void showError(String... messages);
}
```

2. **Logic Component**

![Structure of the Logic Component](docs/images/LogicClassDiagram.png)

```java
public interface Logic {
    CommandResult execute(String commandText);
    ObservableList<Activity> getFilteredActivityList();
}
```

3. **Model Component**

![Structure of the Model Component](docs/images/ModelClassDiagram.png)

```java
public interface Model {
    void addActivity(Activity activity);
    void deleteActivity(Activity target);
    void updateActivity(Activity target, Activity editedActivity);
}
```

4. **Storage Component**

![Structure of the Storage Component](docs/images/StorageClassDiagram.png)

```java
public interface Storage {
    Optional<ReadOnlyHobbyList> readHobbyList();
    void saveHobbyList(ReadOnlyHobbyList hobbyList);
}
```

## 💻 Technical Implementation

### Command Processing

```java
// Example command execution flow
public CommandResult execute(String commandText) {
    Command command = parser.parseCommand(commandText);
    return command.execute(model);
}
```

### Activity Management

```java
public class Activity {
    private final Name name;
    private final Description description;
    private final Status status;
    private final Rating rating;
    private final Review review;
    private final Set<Tag> tags;
    // ... other fields
}
```

### Theme System

```java
public enum Theme {
    LIGHT("light.css"),
    DARK("dark.css"),
    HIGH_CONTRAST("high-contrast.css");

    private final String cssFile;
    // ... implementation
}
```

## 🚀 Getting Started

### Prerequisites

- JDK 11 or above
- JavaFX SDK
- Gradle/Maven (for building)

### Installation

```bash
# Clone the repository
git clone https://github.com/yourusername/hobbylist.git

# Navigate to project directory
cd hobbylist

# Build the project
./gradlew build

# Run the application
./gradlew run
```

### Quick Start Commands

```bash
# Add a new activity
add n/Learn Guitar d/Weekly practice sessions s/ONGOING

# List all activities
list

# Mark activity as completed
done 1

# Rate an activity
rate 1 r/5 rev/Great experience!
```

## 📊 Use Cases

### Add Activity

```
1. User enters activity details
2. System validates input
3. System adds activity
4. System shows confirmation
```

### Delete Activity

```
1. User requests activity list
2. System displays activities
3. User specifies activity to delete
4. System removes activity
```

## 🔧 Development

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── hobbylist/
│   │   │   ├── commons/
│   │   │   ├── logic/
│   │   │   ├── model/
│   │   │   ├── storage/
│   │   │   └── ui/
│   │   └── META-INF/
│   └── resources/
│       └── view/
└── test/
    └── java/
        └── hobbylist/
```

### Testing

```bash
# Run all tests
./gradlew test

# Run specific test category
./gradlew test --tests "hobbylist.logic.*"
```

## 📘 Documentation

### [User Guide](https://ay2223s1-cs2103t-t12-3.github.io/tp/UserGuide.html)

- Quick Start
- Features
- Command Reference
- FAQs

### [Developer Guide](https://ay2223s1-cs2103t-t12-3.github.io/tp/DeveloperGuide.html)

- Setting Up
- Design
- Implementation
- Testing
- DevOps

## 🛠️ Built With

- Java 11
- JavaFX
- Jackson (JSON processing)
- JUnit 5 (Testing)
