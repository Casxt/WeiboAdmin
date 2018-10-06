import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauditComponent } from './unaudit.component';

describe('UnauditComponent', () => {
  let component: UnauditComponent;
  let fixture: ComponentFixture<UnauditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnauditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
