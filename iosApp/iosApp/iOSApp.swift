import ComposeApp
import SwiftUI

@main
struct iOSApp: App {
    init() {
        AppModuleKt.initializeKoin()
        NapierProxyKt.debugBuild()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}