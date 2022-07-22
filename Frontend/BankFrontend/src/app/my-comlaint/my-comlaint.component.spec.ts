import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyComlaintComponent } from './my-comlaint.component';

describe('MyComlaintComponent', () => {
  let component: MyComlaintComponent;
  let fixture: ComponentFixture<MyComlaintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyComlaintComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyComlaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
