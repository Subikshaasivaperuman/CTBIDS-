# Selenium MCP Server

This is a Model Context Protocol (MCP) server that provides Selenium web automation capabilities to AI applications through a standardized interface.

## Features

- Navigate to URLs
- Click elements using CSS selectors
- Enter text into input fields
- Extract text from elements
- Check element existence on page

## Prerequisites

- Java 17 or higher
- Maven
- Chrome browser (for Selenium WebDriver)

## Build Instructions

To build the project:

```bash
mvn clean install
```

This will create an executable JAR file in the `target` directory.

## Usage with Claude Desktop

1. Install Claude Desktop from https://claude.ai/download
2. Create or edit the Claude configuration file:
   - Windows: `%APPDATA%\Claude\claude_desktop_config.json`
   - macOS: `~/Library/Application Support/Claude/claude_desktop_config.json`

3. Add the following configuration:

```json
{
  "mcpServers": {
    "selenium-automation": {
      "command": "java",
      "args": [
        "-jar",
        "PATH_TO_JAR/selenium-mcp-server-1.0-SNAPSHOT.jar"
      ]
    }
  }
}
```

Replace `PATH_TO_JAR` with the absolute path to the built JAR file.

4. Restart Claude Desktop

## Available Tools

The server provides the following Selenium automation tools:

- `navigateToUrl`: Navigate to a specified URL
- `clickElement`: Click on an element using CSS selector
- `enterText`: Enter text into an input field
- `getText`: Extract text from an element
- `elementExists`: Check if an element exists on the page

## Security Notes

- The server runs in headless mode by default for security
- All operations require explicit user approval through the Claude Desktop interface
- Users can review and approve/deny each automation action before execution
