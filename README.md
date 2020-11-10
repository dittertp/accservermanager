# AC Competizione Server Manager

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=grimsi_accservermanager&metric=alert_status)](https://sonarcloud.io/dashboard?id=grimsi_accservermanager) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=grimsi_accservermanager&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=grimsi_accservermanager) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=grimsi_accservermanager&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=grimsi_accservermanager) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=grimsi_accservermanager&metric=security_rating)](https://sonarcloud.io/dashboard?id=grimsi_accservermanager) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=grimsi_accservermanager&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=grimsi_accservermanager) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=grimsi_accservermanager&metric=bugs)](https://sonarcloud.io/dashboard?id=grimsi_accservermanager)

## This project is currently abandoned and not actively updated!
Mainly due to the lack of time I have at the moment and also because I don't play ACC any more.
I don't know if I will ever pick this up again, but if someone wants to develop and update it, feel free to contact me.

## Introduction
This is the backend & frontend component of my AC Competizione Server Manager.
The server manager consists of a Java Spring based backend and a Angular 7 based frontend.

For instructions on how to install this server manager, please head over to the [wiki](https://github.com/grimsi/accservermanager/wiki).

## Goal of this project
This project aims to provide a robust and reliable management interface to manage a great number of gameserver instances for AC Competizione.
The main goal for me is to learn how to work with a lot of different cutting edge technologies, so don't expect too much from my source code since most of the stuff I will work with is pretty new to me.

## Technology Stack
The frontend is built using Angular 7 and Angular Material to provide a nice-looking, smooth UI.
The backend is built using Java Spring Boot, Spring Metrics, Spring Security and Spring Data.
MongoDB is used to persist data.
The server instances are hosted in dedicated docker containers which are managed with the [Spotify Docker Client for Java](https://github.com/spotify/docker-client).

## Current functionality
You can create your own events and link them to servers.
You can also manage your servers (start/stop/pause/edit/delete/create) and monitor their status (running/paused/stopped).

## Next steps
I will try to implement new features such as the ability to view results and server logs.

## License
This source code is licensed under the ["Dont Be a Dick" Public License](https://github.com/grimsi/accservermanager/blob/master/LICENSE).
