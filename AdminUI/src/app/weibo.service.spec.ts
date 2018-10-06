import { TestBed } from '@angular/core/testing';

import { WeiboService } from './weibo.service';

describe('WeiboService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WeiboService = TestBed.get(WeiboService);
    expect(service).toBeTruthy();
  });
});
