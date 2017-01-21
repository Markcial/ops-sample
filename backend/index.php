<?php

require_once __DIR__ . '/vendor/autoload.php';


$loader = new Twig_Loader_Filesystem(__DIR__ . '/templates');
$app = new Laravel\Lumen\Application(__DIR__);
$twig = new Twig_Environment($loader);

$app->get('/', function () use ($twig) {
    return $twig->load('index.html')->render();
});

$app->run();