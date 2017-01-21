<?php

require_once __DIR__ . '/vendor/autoload.php';


$app = new Laravel\Lumen\Application(__DIR__);

$app->get('/foo', function () {
    return 'Hello World';
});

$app->run();