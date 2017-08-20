-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema lto
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `lto` ;

-- -----------------------------------------------------
-- Schema lto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lto` DEFAULT CHARACTER SET utf8 ;
USE `lto` ;

-- -----------------------------------------------------
-- Table `lto`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Address` ;

CREATE TABLE IF NOT EXISTS `lto`.`Address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(100) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(100) NULL,
  `country` VARCHAR(100) NULL,
  `postalCode` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`UserType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`UserType` ;

CREATE TABLE IF NOT EXISTS `lto`.`UserType` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`User` ;

CREATE TABLE IF NOT EXISTS `lto`.`User` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'DB unique identifier.',
  `name` VARCHAR(45) NULL,
  `userName` VARCHAR(100) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `userType` BIGINT(20)  NULL,
  `dateOfBirth` DATE NULL,
  `emailAddress` VARCHAR(100) NULL,
  `profilePicture` VARCHAR(100) NULL COMMENT 'S3 image URL.',
  `address` BIGINT(20) NULL,
  `userRole` VARCHAR(45) NOT NULL DEFAULT 'VLNTR' COMMENT 'Role of user. Default registration user’s role is as a volunteer.',
  `phoneNumber` VARCHAR(45) NULL,
  `privacyMode` VARCHAR(45) NULL,
  `resetPassordToken` VARCHAR(500) NULL,
  `accountVerified` TINYINT(1) NULL DEFAULT '0',
  `gender` VARCHAR(45) NULL,
  `resetPasswordExpiry` DATE NULL,
  `resetPasswordVerify` BIT(1) NOT NULL DEFAULT 0,
  `summary` VARCHAR(1000) NULL DEFAULT NULL,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `lastName` VARCHAR(45) NULL DEFAULT NULL,
  `modifiedDate` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Address1_idx` (`address` ASC),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC),
  INDEX `fk_User_UserType1_idx` (`userType` ASC),
  CONSTRAINT `fk_User_Address1`
    FOREIGN KEY (`address`)
    REFERENCES `lto`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_UserType1`
    FOREIGN KEY (`userType`)
    REFERENCES `lto`.`UserType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`SocialAppType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`SocialAppType` ;

CREATE TABLE IF NOT EXISTS `lto`.`SocialAppType` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `authUrl` VARCHAR(500) NULL,
  `authMethod` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`UserSocialConnect`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`UserSocialConnect` ;

CREATE TABLE IF NOT EXISTS `lto`.`UserSocialConnect` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userID` BIGINT(20) NOT NULL,
  `socialAppID` VARCHAR(200) NOT NULL,
  `socialAppTypeID` BIGINT(20) NOT NULL,
  `refreshTokenID` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `socialConnectToUser_FK_idx` (`userID` ASC),
  INDEX `socialConnectToApp_Fk_idx` (`socialAppTypeID` ASC),
  CONSTRAINT `socialConnectToUser_FK`
    FOREIGN KEY (`userID`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `socialConnectToApp_Fk`
    FOREIGN KEY (`socialAppTypeID`)
    REFERENCES `lto`.`SocialAppType` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`Organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Organization` ;

CREATE TABLE IF NOT EXISTS `lto`.`Organization` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `managedBy` BIGINT(20) NOT NULL,
  `name` VARCHAR(100) NULL,
  `emailAddress` VARCHAR(500) NULL,
  `createdOn` DATE NULL,
  `website` VARCHAR(100) NULL,
  `rating` VARCHAR(45) NULL,
  `orgType` VARCHAR(45) NOT NULL,
  `pageTheme` VARCHAR(45) NULL COMMENT 'Theme details.',
  `bannerPicture` VARCHAR(500) NULL COMMENT 'Banner image URL.',
  `address` BIGINT(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `ngoManagedByUser_FK_idx` (`managedBy` ASC),
  INDEX `fk_Ngo_Address1_idx` (`address` ASC),
  CONSTRAINT `ngoManagedByUser_FK`
    FOREIGN KEY (`managedBy`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ngo_Address1`
    FOREIGN KEY (`address`)
    REFERENCES `lto`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`Party`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Party` ;

CREATE TABLE IF NOT EXISTS `lto`.`Party` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `partyType` VARCHAR(45)  NULL,
  `organization` BIGINT(20) NULL,
  `user` BIGINT(20) NULL,
  `rating` FLOAT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Party_Ngo1_idx` (`organization` ASC),
  INDEX `fk_Party_User1_idx` (`user` ASC),
  CONSTRAINT `fk_Party_Ngo1`
    FOREIGN KEY (`organization`)
    REFERENCES `lto`.`Organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Party_User1`
    FOREIGN KEY (`user`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`Opportunity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Opportunity` ;

CREATE TABLE IF NOT EXISTS `lto`.`Opportunity` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `type` VARCHAR(45) NULL COMMENT 'Program or opportunity.',
  `dateStart` DATETIME NULL,
  `dateEnd` DATETIME NULL,
  `createdByParty` BIGINT(20) NOT NULL,
  `bannerImage` VARCHAR(500) NULL,
  `description` VARCHAR(1000) NULL,
  `address` BIGINT(20)  NULL,
  `latLong` VARCHAR(100) NULL,
  `parentProgram` BIGINT(20) NULL,
  `contactPerson` BIGINT(20) NULL,
  `modifiedDate` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Opportunity_Address1_idx` (`address` ASC),
  INDEX `fk_Opportunity_PartyCreator_idx` (`createdByParty` ASC),
  INDEX `fk_Opportunity_ContactPerson_idx` (`contactPerson` ASC),
  CONSTRAINT `fk_Opportunity_PartyCreator`
    FOREIGN KEY (`createdByParty`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Opportunity_Address1`
    FOREIGN KEY (`address`)
    REFERENCES `lto`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Opp_Parent`
    FOREIGN KEY (`parentProgram`)
    REFERENCES `lto`.`Opportunity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Opportunity_ContactPerson`
    FOREIGN KEY (`contactPerson`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`Cause`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Cause` ;

CREATE TABLE IF NOT EXISTS `lto`.`Cause` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(500) NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`OpportunityCauseXref`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`OpportunityCauseXref` ;

CREATE TABLE IF NOT EXISTS `lto`.`OpportunityCauseXref` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `opportunity` BIGINT(20) NOT NULL,
  `cause` BIGINT(20) NOT NULL,
  INDEX `fk_OppertunityCategoryMapping_Opportunity1_idx` (`opportunity` ASC),
  INDEX `fk_OppertunityCategoryMapping_OpportunityCategories1_idx` (`cause` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_OppertunityCategoryMapping_Opportunity1`
    FOREIGN KEY (`opportunity`)
    REFERENCES `lto`.`Opportunity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OppertunityCategoryMapping_OpportunityCategories1`
    FOREIGN KEY (`cause`)
    REFERENCES `lto`.`Cause` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`PartyCauseXref`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`PartyCauseXref` ;

CREATE TABLE IF NOT EXISTS `lto`.`PartyCauseXref` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `party` BIGINT(20) NOT NULL,
  `cause` BIGINT(20) NOT NULL,
  `addedOn` DATETIME NULL,
  `status` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_VolunteerInterests_OpportunityCategories1_idx` (`cause` ASC),
  INDEX `fk_PartyInterests_User1_idx` (`party` ASC),
  CONSTRAINT `fk_PartyInterests_User1`
    FOREIGN KEY (`party`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PartyInterests_OpportunityCategories1`
    FOREIGN KEY (`cause`)
    REFERENCES `lto`.`Cause` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`JobType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`JobType` ;

CREATE TABLE IF NOT EXISTS `lto`.`JobType` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(100) NULL,
  `status` VARCHAR(45) NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`PartyParticipation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`PartyParticipation` ;

CREATE TABLE IF NOT EXISTS `lto`.`PartyParticipation` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `party` BIGINT(20) NOT NULL,
  `opportunity` BIGINT(20) NOT NULL,
  `participationType` VARCHAR(45)  NULL,
  `status` VARCHAR(45) NULL,
  `dateStart` DATETIME NULL,
  `dateEnd` DATETIME NULL,
  `linkedOrg` BIGINT(20) NULL,
  `orgLinkApproved` TINYINT(1) NULL,
  `rating` FLOAT NULL,
  `review` varchar(2000) NULL,
  `jobType` BIGINT(20) NOT NULL,
  `attendance` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Volunteer_Opportunity_idx` (`opportunity` ASC),
  INDEX `fk_VolunteerParticipation_JobType1_idx` (`jobType` ASC),
  INDEX `fk_PartyParticipation_Party_idx` (`party` ASC),
  INDEX `fk_participation_Org_idx` (`linkedOrg` ASC),
  CONSTRAINT `fk_PartyParticipation_Party`
    FOREIGN KEY (`party`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Volunteer_Opportunity`
    FOREIGN KEY (`opportunity`)
    REFERENCES `lto`.`Opportunity` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_participation_Org`
    FOREIGN KEY (`linkedOrg`)
    REFERENCES `lto`.`Organization` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PartyParticipation_JobType1`
    FOREIGN KEY (`jobType`)
    REFERENCES `lto`.`JobType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`Post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Post` ;

CREATE TABLE `Post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `postedOn` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `thumbnailUrl` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `party` bigint(20) DEFAULT NULL,
  `postedTo` bigint(20) DEFAULT NULL,
  `originalPost` bigint(20) DEFAULT NULL,
  `postedForOpportunity` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4pakbcnod978fygwffw0r8mx` (`party`),
  KEY `FK_d25sfb5h0ocgdrs4vyfv5w0uo` (`postedTo`),
  KEY `FK_c4wxu7nsixh5rlgp4jndf1pge` (`originalPost`),
  KEY `FK_3f3bx96q8318unqghhpxd3v12` (`postedForOpportunity`),
  CONSTRAINT `FK_3f3bx96q8318unqghhpxd3v12` FOREIGN KEY (`postedForOpportunity`) REFERENCES `Opportunity` (`id`),
  CONSTRAINT `FK_4pakbcnod978fygwffw0r8mx` FOREIGN KEY (`party`) REFERENCES `Party` (`id`),
  CONSTRAINT `FK_c4wxu7nsixh5rlgp4jndf1pge` FOREIGN KEY (`originalPost`) REFERENCES `Post` (`id`),
  CONSTRAINT `FK_d25sfb5h0ocgdrs4vyfv5w0uo` FOREIGN KEY (`postedTo`) REFERENCES `Party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- -----------------------------------------------------
-- Table `lto`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Comment` ;

CREATE TABLE IF NOT EXISTS `lto`.`Comment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `party` BIGINT(20) NOT NULL,
  `post` BIGINT(20) NOT NULL,
  `type` VARCHAR(45) NULL COMMENT 'Comment/like etc',
  `comment` VARCHAR(1000) NULL,
  `commentedOn` DATETIME NULL,
  `status` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PostComment_Party_idx` (`party` ASC),
  INDEX `fk_comment_post_idx` (`post` ASC),
  CONSTRAINT `fk_PostComment_Party`
    FOREIGN KEY (`party`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post`
    FOREIGN KEY (`post`)
    REFERENCES `lto`.`Post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`UserExperience`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`UserExperience` ;

CREATE TABLE IF NOT EXISTS `lto`.`UserExperience` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user` BIGINT(20) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `organization` BIGINT(20) NULL,
  `title` VARCHAR(100) NULL,
  `startDate` DATE NULL,
  `endDate` DATE NULL,
  `description` VARCHAR(1000) NULL,
  `location` VARCHAR(100) NULL,
  `course` VARCHAR(100) NULL,
  `degree` VARCHAR(100) NULL,
  `cause` VARCHAR(100) NULL,
  `organizationName` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_EducationDetail_User1_idx` (`user` ASC),
  INDEX `fk_orguser_org_idx` (`organization` ASC),
  CONSTRAINT `fk_UserOrgXrefDetail_User1`
    FOREIGN KEY (`user`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orguser_org`
    FOREIGN KEY (`organization`)
    REFERENCES `lto`.`Organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`AppInvite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`AppInvite` ;

CREATE TABLE IF NOT EXISTS `lto`.`AppInvite` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `emailAddress` VARCHAR(100) NULL,
  `socialAppType` BIGINT(20) NOT NULL,
  `invitedBy` BIGINT(20) NOT NULL,
  `invitedOn` DATETIME NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_AppInvite_SocialAppType1_idx` (`socialAppType` ASC),
  INDEX `fk_AppInvite_User1_idx` (`invitedBy` ASC),
  CONSTRAINT `fk_AppInvite_SocialAppType1`
    FOREIGN KEY (`socialAppType`)
    REFERENCES `lto`.`SocialAppType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AppInvite_User1`
    FOREIGN KEY (`invitedBy`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`PartyFeedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`PartyFeedback` ;

CREATE TABLE IF NOT EXISTS `lto`.`PartyFeedback` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `party` BIGINT(20) NOT NULL,
  `givenBy` BIGINT(20) NOT NULL,
  `rating` FLOAT NULL,
  `comments` VARCHAR(2000) NULL,
  `status` VARCHAR(45) NULL,
  `ratedOn` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_NgoRating_Ngo1_idx` (`party` ASC),
  INDEX `fk_NgoRating_User1_idx` (`givenBy` ASC),
  CONSTRAINT `fk_NgoRating_Ngo1`
    FOREIGN KEY (`party`)
    REFERENCES `lto`.`Organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NgoRating_User1`
    FOREIGN KEY (`givenBy`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`OpportunityJobType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`OpportunityJobType` ;

CREATE TABLE IF NOT EXISTS `lto`.`OpportunityJobType` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `jobType` BIGINT(20) NOT NULL,
  `opportunity` BIGINT(20) NOT NULL,
  `numberOfPositions` INT NULL DEFAULT 1,
  `status` VARCHAR(45) NULL,
  `selectionCriteria` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_OpportunityJobType_JobType1_idx` (`jobType` ASC),
  INDEX `fk_OpportunityJobType_Opportunity1_idx` (`opportunity` ASC),
  CONSTRAINT `fk_OpportunityJobType_JobType1`
    FOREIGN KEY (`jobType`)
    REFERENCES `lto`.`JobType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OpportunityJobType_Opportunity1`
    FOREIGN KEY (`opportunity`)
    REFERENCES `lto`.`Opportunity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`Connection`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Connection` ;

CREATE TABLE IF NOT EXISTS `lto`.`Connection` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `party` BIGINT(20) NOT NULL,
  `connectedTo` BIGINT(20) NOT NULL,
  `connected` TINYINT(1) NULL,
  `initiatedOn` DATETIME NULL,
  `connectedOn` DATETIME NULL,
  `connectionType` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Connection_Party_idx` (`party` ASC),
  INDEX `fk_Connection_ConnectedTo_idx` (`connectedTo` ASC),
  CONSTRAINT `fk_Connection_Party`
    FOREIGN KEY (`party`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Connection_ConnectedTo`
    FOREIGN KEY (`connectedTo`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`PartyJobTypeXref`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`PartyJobTypeXref` ;

CREATE TABLE IF NOT EXISTS `lto`.`PartyJobTypeXref` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `jobType` BIGINT(20) NOT NULL,
  `party` BIGINT(20) NOT NULL,
  `status` BIT(1) NOT NULL DEFAULT 1,
  `addedOn` DATETIME NULL,
  `comments` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_PartyPrefJobType_JobType1_idx` (`jobType` ASC),
  INDEX `fk_PartyPrefJobType_Party1_idx` (`party` ASC),
  CONSTRAINT `fk_PartyPrefJobType_JobType1`
    FOREIGN KEY (`jobType`)
    REFERENCES `lto`.`JobType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PartyPrefJobType_Party1`
    FOREIGN KEY (`party`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`VolunteerPref`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`VolunteerPref` ;

CREATE TABLE IF NOT EXISTS `lto`.`VolunteerPref` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `volunteer` BIGINT(20) NOT NULL,
  `type` VARCHAR(45) NULL,
  `day` VARCHAR(45) NULL,
  `startTime` TIME NULL,
  `endTime` TIME NULL,
  `location` VARCHAR(100) NULL,
  `locationMaxRadius` FLOAT NULL,
  `isAvailable` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_VolunteerPref_User1_idx` (`volunteer` ASC),
  CONSTRAINT `fk_VolunteerPref_User1`
    FOREIGN KEY (`volunteer`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`Notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Notification` ;

CREATE TABLE IF NOT EXISTS `lto`.`Notification` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `party` BIGINT(20) NOT NULL,
  `type` VARCHAR(45) NULL,
  `sentOn` DATETIME NULL,
  `content` LONGTEXT NULL,
  `url` VARCHAR(200) NULL,
  `thumbnailUrl` VARCHAR(500) NULL,
  `status` TINYINT(1)  NULL,
  `isRead` TINYINT(1)  NULL DEFAULT 0,
  `params` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_Party_idx` (`party` ASC),
  CONSTRAINT `fk_Notification_Party0`
    FOREIGN KEY (`party`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`Acheivements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Acheivements` ;

CREATE TABLE IF NOT EXISTS `lto`.`Acheivements` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userExperience` BIGINT(20) NOT NULL,
  `type` VARCHAR(45) NULL,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(1000) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Acheivements_UserExperience1_idx` (`userExperience` ASC),
  CONSTRAINT `fk_Acheivements_UserExperience1`
    FOREIGN KEY (`userExperience`)
    REFERENCES `lto`.`UserExperience` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`AdditionalProfileAttribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`AdditionalProfileAttribute` ;

CREATE TABLE IF NOT EXISTS `lto`.`AdditionalProfileAttribute` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`UserTypeAttributeXref`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`UserTypeAttributeXref` ;

CREATE TABLE IF NOT EXISTS `lto`.`UserTypeAttributeXref` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userType` BIGINT(20) NULL,
  `attributeType` BIGINT(20) NOT NULL,
  `compulsory` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_userTypeAttributeType_idx` (`userType` ASC),
  INDEX `fk_UserTypeAttributeXref_AdditionalProfileAttribute1_idx` (`attributeType` ASC),
  CONSTRAINT `fk_userTypeAttributeType`
    FOREIGN KEY (`userType`)
    REFERENCES `lto`.`UserType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserTypeAttributeXref_AdditionalProfileAttribute1`
    FOREIGN KEY (`attributeType`)
    REFERENCES `lto`.`AdditionalProfileAttribute` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `lto`.`VolunteerLocationPref`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `VolunteerLocationPref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VolunteerLocationPref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `volunteer` bigint(20) NOT NULL,
  `locationAreaName` varchar(45) DEFAULT NULL,
  `locationCity` varchar(45) DEFAULT NULL,
  `locationPostalCode` varchar(45) NOT NULL,
  `includeSurroundingAreas` tinyint(1) DEFAULT '1',
  `modifiedDate` date DEFAULT NULL,
  `insertedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;


-- -----------------------------------------------------
-- Table `lto`.`UserAdditionalProfileAttribute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`UserAdditionalProfileAttribute` ;

CREATE TABLE IF NOT EXISTS `lto`.`UserAdditionalProfileAttribute` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user` BIGINT(20) NULL,
  `attributeType` BIGINT(20) NOT NULL,
  `value` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_UserTypeAttributeXref_AdditionalProfileAttribute1_idx` (`attributeType` ASC),
  INDEX `fk_userAttributeType0_idx` (`user` ASC),
  CONSTRAINT `fk_userAttributeType0`
    FOREIGN KEY (`user`)
    REFERENCES `lto`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserTypeAttributeXref_AdditionalProfileAttribute10`
    FOREIGN KEY (`attributeType`)
    REFERENCES `lto`.`AdditionalProfileAttribute` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `lto`.`PrivacySettings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`PrivacySettings` ;
CREATE TABLE `lto`.`PrivacySettings` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user` BIGINT(20) NOT NULL,
  `mobileNumberVisibility` TINYINT(1) NULL DEFAULT 0,
  `profileDetailsVisibility` TINYINT(1) NULL DEFAULT 0,
  `emailAlertsOn` TINYINT(1) NULL DEFAULT 0,
  `emailNotificationFrequency` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_name_user_idx` (`user` ASC),
  CONSTRAINT `fk_PrivacySettings_User1`
    FOREIGN KEY (`user`)
    REFERENCES `lto`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `lto`.`UserTypeXref`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`UserTypeXref` ;
CREATE TABLE `lto`.`UserTypeXref` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user` BIGINT(20) NOT NULL,
  `userType` BIGINT(20) NOT NULL,
  `active` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_user_idx` (`user` ASC),
  INDEX `fk_user_type_idx` (`userType` ASC),
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user`)
    REFERENCES `lto`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_type`
    FOREIGN KEY (`userType`)
    REFERENCES `lto`.`usertype` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
       
--   -- -----------------------------------------------------
-- Table `lto`.`Message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lto`.`Message` ;
    CREATE TABLE 'lto'.'Message' (
     `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    'sentPartyStatus' TINYINT(1) NULL,
    'receivedPartyStatus' TINYINT(1) NULL,
    'isRead' TINYINT(1) NULL,
    `sentTime` DATETIME NULL,
     `readTime` DATETIME NULL,
  `fromParty` bigint(20) NOT NULL,
  `toParty` bigint(20) NOT NULL,
  `url` VARCHAR(200) NULL,
  `attachmentUrl` VARCHAR(500) NULL,
  `messageText` VARCHAR(20000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Messaging_fromParty_idx` (`fromParty` ASC),
  INDEX `fk_Messaging_toParty_idx` (`toParty` ASC),
  CONSTRAINT `fk_Messaging_fromParty`
    FOREIGN KEY (`fromParty`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messaging_toParty`
    FOREIGN KEY (`toParty`)
    REFERENCES `lto`.`Party` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
);

CREATE TABLE `OpportunityInvite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accepted` bit(1) DEFAULT NULL,
  `inviteAcceptedDate` datetime DEFAULT NULL,
  `invitedDate` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `invitedByParty` bigint(20) DEFAULT NULL,
  `invitedParty` bigint(20) DEFAULT NULL,
  `jobType` bigint(20) DEFAULT NULL,
  `opportunity` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_oanhfvaguf9cq8ulw63ctftyb` (`invitedByParty`),
  KEY `FK_k066jwd7nc8a0eyah74cloi5` (`invitedParty`),
  KEY `FK_6ytm0pwp5rgo36egxrmvw83y6` (`jobType`),
  KEY `FK_5goxb2cd6d393fcvwtxhy879e` (`opportunity`),
  CONSTRAINT `FK_5goxb2cd6d393fcvwtxhy879e` FOREIGN KEY (`opportunity`) REFERENCES `Opportunity` (`id`),
  CONSTRAINT `FK_6ytm0pwp5rgo36egxrmvw83y6` FOREIGN KEY (`jobType`) REFERENCES `JobType` (`id`),
  CONSTRAINT `FK_k066jwd7nc8a0eyah74cloi5` FOREIGN KEY (`invitedParty`) REFERENCES `Party` (`id`),
  CONSTRAINT `FK_oanhfvaguf9cq8ulw63ctftyb` FOREIGN KEY (`invitedByParty`) REFERENCES `Party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



    
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



