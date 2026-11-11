# Spring Boot Grok Demo

A small Spring Boot chat application that sends prompts to the Groq API using Spring's `RestClient`. The browser UI displays AI responses with Markdown formatting.

## Requirements

- Java 21
- Maven
- A Groq API key

## Configuration

Set your API key as an environment variable before starting the application. The configured model is `openai/gpt-oss-120b`.

macOS / Linux:

```bash
export GROQ_API_KEY="your_groq_api_key"
```

Windows PowerShell:

```powershell
$env:GROQ_API_KEY = "your_groq_api_key"
```

Do not commit API keys to source control.

## Run

From the project directory:

```bash
mvn spring-boot:run
```

Open the chat UI at <http://localhost:8080>.

## API

Send a chat prompt with `POST /api/chat`:

```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"Explain LangChain in one paragraph."}'
```

The response contains the AI reply:

```json
{
  "reply": "..."
}
```

## Swagger UI

With the application running, open <http://localhost:8080/swagger-ui/index.html>. The generated OpenAPI document is available at <http://localhost:8080/v3/api-docs>.
