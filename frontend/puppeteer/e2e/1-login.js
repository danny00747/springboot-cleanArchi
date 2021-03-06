const puppeteer = require('puppeteer')
const { expect } = require('chai')

describe('E2E TESTS FOR LOGIN PAGE', async () => {
  // slowMo: 20,
  let browser
  let page

  before(async () => {
    browser = await puppeteer.launch({
      headless: false,
      defaultViewport: null,
      slowMo: 35,
      args: [
        '--window-size=1920,1080'
      ]
    })
  })

  beforeEach(async () => {
    page = await browser.newPage()
    await page.goto('http://localhost:4200/login')
  })

  after(async () => {
    await browser.close()
  })

  describe('/POST Login', async () => {
    it('it should login a user', async () => {
      expect(await page.$eval('#formTitle', e => e.innerText)).to.eql('LogIn:');

      expect(await page.$eval('#submitBtn', btn => btn.disabled)).eql(true);

      const pseudo = await page.$('#pseudo');
      const password = await page.$('#password');
      const submit = await page.$('#submitBtn');

      await pseudo.click({ clickCount: 3 });
      await pseudo.type('toto');

      await password.click({ clickCount: 3 });
      await password.type('toto');

      expect(await page.$eval('#submitBtn', btn => btn.disabled)).eql(false);

      await submit.click();
      await page.waitForNavigation();

      expect(page.url()).eql('http://localhost:4200/gallery');

      await page.waitFor(1000);
    })

    it('it should NOT login a user with incorrect credentials', async () => {
      const pseudo = await page.$('#pseudo');
      const password = await page.$('#password');
      const submit = await page.$('#submitBtn');

      await pseudo.click({ clickCount: 3 });
      await pseudo.type('********');

      await password.click({ clickCount: 3 });
      await password.type('*****');

      await submit.click();
      await page.waitFor(1000);

      expect(page.url()).eql('http://localhost:4200/login');
    })

    it('it should display form validation on all fields', async () => {
      const pseudo = await page.$('#pseudo');
      const password = await page.$('#password');

      await pseudo.click({ clickCount: 3 });
      await pseudo.type('');

      await password.click({ clickCount: 3 });
      await password.type('');

      await pseudo.click({ clickCount: 3 });
      await page.waitFor(1000);

      expect((await page.$eval('#pseudo', e => e.className)).includes('is-invalid')).eql(true);

      expect(await page.$eval('#pseudoError', e => e.innerText)).eql('This field is required');

      expect((await page.$eval('#password', e => e.className)).includes('is-invalid')).eql(true);

      expect(await page.$eval('#passwordError', e => e.innerText)).eql('A password is required');

      expect(await page.$eval('#submitBtn', btn => btn.disabled)).eql(true);

      expect(page.url()).eql('http://localhost:4200/login');
    })
  })
})
