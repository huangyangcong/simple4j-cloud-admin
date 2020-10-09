(global => {
  global.__POWERED_BY_QIANKUN__ || (__webpack_public_path__ = window.__INJECTED_PUBLIC_PATH_BY_QIANKUN__);
  global['purehtml'] = {
    bootstrap: () => {
      console.log('purehtml bootstrap');
      return Promise.resolve();
    },
    mount: () => {
      console.log('purehtml mount');
      return render($);
    },
    unmount: () => {
      console.log('purehtml unmount');
      return Promise.resolve();
    },
  };
})(window);
