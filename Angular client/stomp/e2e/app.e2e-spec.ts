import { StompPage } from './app.po';

describe('stomp App', () => {
  let page: StompPage;

  beforeEach(() => {
    page = new StompPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
