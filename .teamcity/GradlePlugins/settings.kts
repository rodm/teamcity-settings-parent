package GradlePlugins

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.versionedSettings
import jetbrains.buildServer.configs.kotlin.v10.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a single
TeamCity project. TeamCity looks for the 'settings.kts' file in a
project directory and runs it if it's found, so the script name
shouldn't be changed and its package should be the same as the
project's external id.

The script should contain a single call to the project() function
with a Project instance or an init function as an argument, you
can also specify both of them to refine the specified Project in
the init function.

VcsRoots, BuildTypes, and Templates of this project must be
registered inside project using the vcsRoot(), buildType(), and
template() methods respectively.

Subprojects can be defined either in their own settings.kts or by
calling the subProjects() method in this project.
*/

version = "10.0"
project {
    uuid = "9c0cdb51-9e0c-49cb-bc7c-9ca611cc7fd3"
    extId = "GradlePlugins"
    name = "Gradle Plugins"

    subProject {
        uuid = "2c4c777e-bf5d-4eaf-8e46-eea999fdbd89"
        extId = "GradleTeamCityPlugin"
        name = "Gradle TeamCity Plugin"

        val settingsVcs = GitVcsRoot({
            uuid = "723408f3-cc0c-42da-b348-dedd4bc030ef"
            extId = "TeamcitySettings"
            name = "teamcity-settings"
            url = "https://github.com/rodm/teamcity-settings"
        })
        vcsRoot(settingsVcs)

        features {
            versionedSettings {
                id = "PROJECT_EXT_1"
                mode = VersionedSettings.Mode.ENABLED
                buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
                rootExtId = "TeamcitySettings"
                showChanges = true
                settingsFormat = VersionedSettings.Format.KOTLIN
            }
        }
    }
}
