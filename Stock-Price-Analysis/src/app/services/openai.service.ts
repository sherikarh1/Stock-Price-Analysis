import { Injectable } from '@angular/core';
import { Configuration, OpenAIApi } from 'openai';

@Injectable({
  providedIn: 'root'
})
export class OpenaiService {

  private openai: OpenAIApi;
  configuration = new Configuration({
    apiKey: "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",
  });

  constructor() {
    this.openai = new OpenAIApi(this.configuration);
  }

  generateText(prompt: string):Promise<string | undefined>{
   return this.openai.createCompletion({
        model: "text-davinci-003",
        prompt: prompt,
        max_tokens: 256
      }).then(response => {
        return response.data.choices[0].text;
      }).catch(error=>{
        return '';
      });
  }
}