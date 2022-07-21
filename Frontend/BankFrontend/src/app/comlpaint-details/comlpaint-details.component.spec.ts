import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComlpaintDetailsComponent } from './comlpaint-details.component';

describe('ComlpaintDetailsComponent', () => {
  let component: ComlpaintDetailsComponent;
  let fixture: ComponentFixture<ComlpaintDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComlpaintDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComlpaintDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
