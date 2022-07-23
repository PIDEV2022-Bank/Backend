import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';
import {StorageService} from "../_services/storage.service";

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  constructor(public auth: StorageService) {
  }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.auth.getUser().token}`
      }
    });
    return next.handle(request);
  }

}
