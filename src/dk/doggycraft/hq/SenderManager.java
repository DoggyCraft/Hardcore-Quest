package dk.doggycraft.hq;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SenderManager
{
	private HardcoreQuest				plugin				= null;
	private FileConfiguration	senderConfig		= null;
	private File				senderConfigFile	= null;

	SenderManager(HardcoreQuest p)
	{
		this.plugin = p;
	}

	public void load()
	{
		if (this.senderConfigFile == null)
		{
			this.senderConfigFile = new File(plugin.getDataFolder(), "sender.yml");
		}

		this.senderConfig = YamlConfiguration.loadConfiguration(senderConfigFile);

		this.plugin.log("Loaded " + senderConfig.getKeys(false).size() + " senders.");
	}

	public void save()
	{
		if ((this.senderConfig == null) || (senderConfigFile == null))
		{
			return;
		}

		try
		{
			this.senderConfig.save(senderConfigFile);
		}
		catch (Exception ex)
		{
			this.plugin.log("Could not save config to " + senderConfigFile + ": " + ex.getMessage());
		}
	}

	public void setLastQuestTime(String playerName)
	{
		String pattern = "HH:mm dd-MM-yyyy";
		DateFormat formatter = new SimpleDateFormat(pattern);
		Date thisDate = new Date();

		this.senderConfig.set(playerName + ".LastQuestTime", formatter.format(thisDate));

		save();
	}

	
	public void answer(String playerName, String answer)
	{

	}

	public void setOriginalRank(String playerName, String oldRank)
	{
		senderConfig.set(playerName + ".OriginalRank", oldRank);
		save();
	}

	public String getOriginalRank(String playerName)
	{
		return senderConfig.getString(playerName + ".OriginalRank");
	}
	
	public void setPassedExam(String playerName, String exam)
	{
		List<String> passedQuests = getPassedQuests(playerName);
		
		passedQuests.add(exam);
		
		senderConfig.set(playerName + ".PassedQuests", passedQuests);		
		
		save();
	}

	public List<String> getPassedQuests(String playerName)
	{
		return senderConfig.getStringList(playerName + ".PassedQuests");		
	}

	public boolean signupForQuest(String playerName, String questName)
	{
		senderConfig.set(playerName + ".Quest", questName);
		senderConfig.set(playerName + ".QuestCorrectAnswers", 0);
		senderConfig.set(playerName + ".QuestProgressIndex", -1);

		save();

		return true;
	}

	/*
	public boolean isSender(String playerName)
	{
		return senderConfig.getString(playerName + ".Quest") != null;
	}
	*/

	public boolean isDoingQuest(String playerName)
	{
		return senderConfig.getInt(playerName + ".QuestProgressIndex") > -1;
	}

	public int nextQuestQuestion(String playerName)
	{
		int questionIndex = senderConfig.getInt(playerName + ".QuestProgressIndex");

		questionIndex++;

		senderConfig.set(playerName + ".QuestProgressIndex", questionIndex);

		save();

		return questionIndex;
	}

	public int getQuestProgressIndexForSender(String playerName)
	{
		return senderConfig.getInt(playerName + ".QuestProgressIndex");
	}

	public int getQuestQuestionIndexForSender(String playerName)
	{
		List<String> questions = senderConfig.getStringList(playerName + ".QuestQuestionIndices");
		int questProgressIndex = senderConfig.getInt(playerName + ".QuestProgressIndex");

		return Integer.parseInt((String) questions.get(questProgressIndex));
	}

	public void setQuestQuestionForSender(String playerName, String question, List<String> options, String correctOption)
	{
		senderConfig.set(playerName + ".QuestQuestion", question);
		senderConfig.set(playerName + ".QuestQuestionOptions", options);
		senderConfig.set(playerName + ".QuestCorrectOption", correctOption);

		save();
	}

	public String getQuestQuestionForSender(String playerName)
	{
		return senderConfig.getString(playerName + ".QuestQuestion");
	}

	public List<String> getQuestQuestionOptionsForSender(String playerName)
	{
		return senderConfig.getStringList(playerName + ".QuestQuestionOptions");
	}

	public void setQuestForSender(String playerName, String examName, List<String> questions)
	{
		senderConfig.set(playerName + ".Quest", examName);
		senderConfig.set(playerName + ".QuestProgressIndex", Integer.valueOf(-1));
		senderConfig.set(playerName + ".QuestCorrectAnswers", Integer.valueOf(0));
		senderConfig.set(playerName + ".QuestQuestionIndices", questions);

		plugin.logDebug("Setting question indices of size " + questions.size());

		save();
	}

	public int getCorrectAnswersForSender(String playerName)
	{
		return senderConfig.getInt(playerName + ".QuestCorrectAnswers");
	}

	public String getQuestForSender(String believerName)
	{
		return senderConfig.getString(believerName + ".Quest");
	}

	public Set<String> getSenders()
	{
		Set<String> allSenders = senderConfig.getKeys(false);

		return allSenders;
	}

	public void removeSender(String senderName)
	{
		senderConfig.set(senderName + ".Quest", null);
		senderConfig.set(senderName + ".QuestProgressIndex", -1);
		senderConfig.set(senderName + ".QuestQuestionIndices", null);
		senderConfig.set(senderName + ".QuestQuestion", null);
		senderConfig.set(senderName + ".QuestQuestionOptions", null);
		senderConfig.set(senderName + ".QuestCorrectOption", null);
		senderConfig.set(senderName + ".QuestCorrectAnswers", null);
		senderConfig.set(senderName + ".OriginalRank", null);

		plugin.logDebug(senderName + " was removed as sender");

		save();
	}

	public void deleteSender(String senderName)
	{
		senderConfig.set(senderName, null);

		save();
	}
}