import Application from 'App';
import React from 'react';
import ReactDOM from 'react-dom';

document.addEventListener('DOMContentLoaded', () => {
  // do your setup here
  console.log('Initialized app');

  ReactDOM.render(
      <Application />,
      document.querySelector('#app')
  );
});
