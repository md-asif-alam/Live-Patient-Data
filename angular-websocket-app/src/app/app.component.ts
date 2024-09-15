// import { Component, OnInit } from '@angular/core';
// import { RouterOutlet } from '@angular/router';
// import { WebsocketService } from './websocket.service';

// @Component({
//   selector: 'app-root',
//   standalone: true,
//   imports: [RouterOutlet],
//   templateUrl: './app.component.html',
//   styleUrl: './app.component.css'
// })
// export class AppComponent implements OnInit {
//   title = 'angular-websocket-app';
//   streamStarted: boolean = false;
//   // streamPaused: boolean= false;
//   temperature = 0;
//   bloodPressure = 0;
//   private intervalId: any;
//   data: string | undefined;

//   constructor(private websocketService: WebsocketService) {}

//   ngOnInit() {
    
//   }

//   ngOnDestroy() {
//     this.stopStreaming();
//   }

//   startStreaming(): void {
//     this.streamStarted = true;

//     this.intervalId = setInterval(() => {
//       this.getLiveData();
//     }, 5000);
//   }

//   // stopStreaming(): void {
//   //   console.log("stream ended");
//   //   this.streamStarted = false;
//   //   if (this.intervalId) {
//   //     clearInterval(this.intervalId);
//   //   }
//   // }

//   stopStreaming(): void {
//     console.log("Stopping stream...");
//     // this.streamPaused = true;
//     this.streamStarted = false;
//     this.temperature=0;
//     this.bloodPressure=0;
//     if (this.intervalId) {
//       clearInterval(this.intervalId);
//       this.intervalId = false; // Optional: clear the intervalId reference
//       // this.resetData();
//       console.log("Stream stopped successfully.");
//     } else {
//       console.log("No interval to clear.");
//     }
//   }

//   getLiveData(): void {
//     if(this.streamStarted) {
//       console.log('getting data');
//       this.websocketService.getMessages().subscribe((message) => {
//         console.log("Message");
//         this.temperature = message.temperature;
//         this.bloodPressure = message.bloodPressure;
//       });
//     }
//   }

//   // private resetData(): void {
//   //   // Reset data or state to "0 0"
//   //   this.temperature=0;
//   //   this.bloodPressure=0;
//   //   console.log("Data reset to:", this.data);
//   // }
// }



import { Component, OnInit, OnDestroy } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { WebsocketService } from './websocket.service';
import { Subscription, interval } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'angular-websocket-app';
  streamStarted: boolean = false;
  temperature = 0;
  bloodPressure = 0;
  private intervalSubscription: Subscription | null = null;
  private websocketSubscription: Subscription | null = null;

  constructor(private websocketService: WebsocketService) {}

  ngOnInit() {
    // You can initialize anything here if needed
  }

  ngOnDestroy() {
    this.stopStreaming();
  }

  startStreaming(): void {
    this.streamStarted = true;

    // Create an observable that triggers every 5 seconds
    const source = interval(5000);

    this.intervalSubscription = source.subscribe(() => {
      if (this.streamStarted) {
        this.getLiveData();
      }
    });
  }

  stopStreaming(): void {
    console.log("Stopping stream...");
    this.streamStarted = false;
    this.temperature = 0;
    this.bloodPressure = 0;

    if (this.intervalSubscription) {
      this.intervalSubscription.unsubscribe();
      this.intervalSubscription = null;
      console.log("Interval stopped successfully.");
    } else {
      console.log("No interval to clear.");
    }

    if (this.websocketSubscription) {
      this.websocketSubscription.unsubscribe();
      this.websocketSubscription = null;
      console.log("WebSocket subscription stopped successfully.");
    }
  }

  getLiveData(): void {
    if (this.streamStarted) {
      console.log('Getting data');
      
      // Make sure to unsubscribe from the previous WebSocket subscription
      if (this.websocketSubscription) {
        this.websocketSubscription.unsubscribe();
      }

      this.websocketSubscription = this.websocketService.getMessages().subscribe((message) => {
        console.log("Message received");
        this.temperature = message.temperature;
        this.bloodPressure = message.bloodPressure;
      });
    }
  }
}
