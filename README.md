# 🎵 PanTracklist

**PanTracklist** is a native Android application that displays a list of music albums. 
It supports offline usage and leverages a modern Android stack including Jetpack Compose, Hilt, Room, Paging3, and Retrofit.

---

## 📦 Features

- ✅ Displays album titles and images
- ✅ Fetches JSON from a remote endpoint at runtime
- ✅ Uses a custom `User-Agent` header for image requests
- ✅ Caches data locally with Room for offline access
- ✅ Supports pagination via Paging 3
- ✅ Handles configuration changes
- ✅ Clean architecture for testability and scalability
- ✅ Unit and instrumentation tests included

---

## 🧱 Architecture

The project follows **Clean Architecture**, separating code into three core layers:


This modular approach ensures:
- Clear separation of concerns
- Easy testing
- Decoupled business logic

---

## 🧰 Tech Stack Highlights

| Purpose              | Library                         |
|----------------------|----------------------------------|
| UI                   | Jetpack Compose                  |
| DI                   | Hilt                             |
| Networking           | Retrofit + OkHttp                |
| JSON Parsing         | Moshi                            |
| Image Loading        | Coil (with custom OkHttp client) |
| Local Storage        | Room                             |
| Pagination           | Paging 3                         |
| Testing              | JUnit5, AssertK, Coroutine Test, MockWebServer, Hilt Testing |

---

## 🚧 Key Implementation Details

### 🔁 Pagination with Paging 3

- Pagination is implemented using the `PagingSource` and `Pager` APIs.
- Data is fetched from the network and cached in Room.
- The app supports infinite scrolling and offline viewing via Paging’s `RemoteMediator`.

### 🌐 Networking with Retrofit

- Data is retrieved from:  
  `https://static.leboncoin.fr/img/shared/technical-test.json`
- Retrofit is configured with a Moshi converter.
- A custom `User-Agent` header is added to all requests, especially for image loading.

### 🖼️ Image Loading with Custom Headers

- Coil is used for image rendering in Compose.
- Coil’s `OkHttpClient` is customized with an `Interceptor` to inject the `User-Agent` header per image request.

### 💾 Offline Persistence with Room

- All fetched album data is stored in a local Room database.
- On app restart or network loss, data is loaded from the local cache.
- Paging3 integrates directly with Room for efficient pagination.

---

## 🧪 Testing Strategy

The app includes:

- ✅ **Unit tests** for UseCases and Repositories
- ✅ **Instrumented tests** using in-memory Room and Hilt test modules
- ✅ **MockWebServer** to mock network responses
- ✅ **UI tests** with Compose Test APIs


---

## 🧠 Design Justifications

### Clean Architecture
- Improves maintainability and testing
- Separates UI, domain, and data layers clearly

### Hilt for DI
- Reduces boilerplate
- Integrates well with Android components and testing

### Paging 3
- Handles large datasets efficiently
- Works seamlessly with Room and Retrofit

### Moshi + Retrofit
- Lightweight and fast JSON parsing
- Easy integration with Retrofit

### Room
- Official Android library
- Simple, reliable local persistence with Flow support

### Compose + Coil
- Modern, declarative UI
- Efficient image loading with header customization



